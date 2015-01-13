package za.co.no9.jsqldsl.port.jsqldslmojo;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.xml.sax.SAXException;
import za.co.no9.jsqldsl.port.jsqldslmojo.configuration.Configuration;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.net.URL;

@Mojo(name = "jsqldsl")
public class JSQLDSLMojo extends AbstractMojo {
    @Parameter(defaultValue = "${basedir}/jsqldsl.xml", alias = "file", required = true)
    private File configurationFile;

    @Override
    public void execute() throws MojoExecutionException {
        getLog().debug("JSQLDSLMojo: Start");
        getLog().debug("JSQLDSLMojo: configurationFile: " + configurationFile.getAbsolutePath());

        try {
            processConfiguration(configurationFile);
        } catch (SAXException | JAXBException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }

        getLog().debug("JSQLDSLMojo: End");
    }

    protected void processConfiguration(File configurationFile) throws JAXBException, SAXException {
        getConfiguration(configurationFile);
    }

    public static Configuration getConfiguration(File configurationFile) throws JAXBException, SAXException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(loadSchema());

        jaxbUnmarshaller.setSchema(schema);
        return (Configuration) jaxbUnmarshaller.unmarshal(configurationFile);
    }

    public static URL loadSchema() {
        return JSQLDSLMojo.class.getResource("/xsd/jsqldsl-8-configuration.xsd");
    }
}
