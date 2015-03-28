package za.co.no9.jsqldsl.port.jsqldslmojo;

import org.xml.sax.SAXException;
import za.co.no9.jsqldsl.drivers.DBDriver;
import za.co.no9.jsqldsl.port.jsqldslmojo.configuration.JdbcType;
import za.co.no9.jsqldsl.port.jsqldslmojo.configuration.TargetType;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Configuration {
    private static final String SCHEMA_RESOURCE_NAME = "/xsd/jsqldsl-8-configuration.xsd";

    private File configurationFile;
    private za.co.no9.jsqldsl.port.jsqldslmojo.configuration.Configuration xmlConfiguration;

    private Configuration(File configurationFile, za.co.no9.jsqldsl.port.jsqldslmojo.configuration.Configuration xmlConfiguration) {
        this.configurationFile = configurationFile;
        this.xmlConfiguration = xmlConfiguration;
    }

    protected Configuration(File configurationFile) throws ConfigurationException, JAXBException {
        this(configurationFile, (za.co.no9.jsqldsl.port.jsqldslmojo.configuration.Configuration) getUnmarshaller().unmarshal(configurationFile));
    }

    public static Configuration from(File configurationFile) throws ConfigurationException {
        try {
            return new Configuration(configurationFile);
        } catch (JAXBException e) {
            throw new ConfigurationException("Unable to parse the configuration file: " + e.getMessage(), e);
        }
    }

    private static Unmarshaller getUnmarshaller() throws ConfigurationException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(za.co.no9.jsqldsl.port.jsqldslmojo.configuration.Configuration.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            jaxbUnmarshaller.setSchema(getSchema());
            return jaxbUnmarshaller;
        } catch (JAXBException ex) {
            throw new ConfigurationException("Unable to create an unmarshaller to parse the configuration file.", ex);
        }
    }

    private static Schema getSchema() throws ConfigurationException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            return schemaFactory.newSchema(loadSchema());
        } catch (SAXException ex) {
            throw new ConfigurationException("Unable to parse the schema " + SCHEMA_RESOURCE_NAME + ".", ex);
        }
    }

    private static URL loadSchema() {
        return JSQLDSLMojo.class.getResource(SCHEMA_RESOURCE_NAME);
    }

    public File getTargetDestination() {
        return new File(configurationFile.getParentFile(), getTargetType().getDestination());
    }

    private TargetType getTargetType() {
        return xmlConfiguration.getTargets().getTarget().get(0);
    }

    public Connection getJDBCConnection() throws SQLException {
        JdbcType jdbcType = xmlConfiguration.getSource().getJdbc();
        try {
            Class.forName(jdbcType.getDriver());
            return DriverManager.getConnection(
                    jdbcType.getUrl(),
                    jdbcType.getUsername(),
                    jdbcType.getPassword());
        } catch (ClassNotFoundException e) {
            throw new SQLException("Loading of the driver class " + jdbcType.getDriver() + " failed:" + e.getMessage(), e);
        } catch (SQLException e) {
            throw new SQLException("Unable to connect: " + e.getMessage(), e);
        }
    }

    public DBDriver getDBDriver() throws ConfigurationException {
        try {
            return (DBDriver) Class.forName(getTargetType().getDriver()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            throw new ConfigurationException("Unable to instantiate the DB handler " + getTargetType().getDriver() + ".", ex);
        }
    }

    public String getTargetPackageName() {
        return getTargetType().getPackage();
    }

    public TableFilter getTableFilter() {
        return new TableFilter(xmlConfiguration.getSource().getIncludes().getInclude());
    }
}
