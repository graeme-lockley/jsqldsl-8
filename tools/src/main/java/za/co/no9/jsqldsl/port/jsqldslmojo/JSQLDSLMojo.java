package za.co.no9.jsqldsl.port.jsqldslmojo;

import freemarker.template.TemplateException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import za.co.no9.jsqldsl.drivers.DBDriver;
import za.co.no9.jsqldsl.tools.DatabaseMetaData;
import za.co.no9.jsqldsl.tools.TableMetaData;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@Mojo(name = "jsqldsl")
public class JSQLDSLMojo extends AbstractMojo {
    @Parameter(defaultValue = "${basedir}/jsqldsl.xml", alias = "file", required = true)
    private File configurationFile;

    @Override
    public void execute() throws MojoExecutionException {
        getLog().debug("JSQLDSLMojo: configurationFile: " + configurationFile.getAbsolutePath());

        try {
            processConfiguration(configurationFile);
        } catch (ConfigurationException | SQLException | IOException | TemplateException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
    }

    protected void processConfiguration(File configurationFile) throws ConfigurationException, SQLException, IOException, TemplateException {
        processConfiguration(Configuration.from(configurationFile));
    }

    protected void processConfiguration(Configuration configuration) throws ConfigurationException, SQLException, IOException, TemplateException {
        DBDriver dbDriver = configuration.getDBDriver();
        File generatorTargetRoot = configuration.getTargetDestination();

        try (Connection connection = configuration.getJDBCConnection()) {
            DatabaseMetaData databaseMetaData = DatabaseMetaData.from(connection);
            TableFilter tableFilter = configuration.getTableFilter();

            for (TableMetaData table : databaseMetaData.tables(null, null)) {
                if (tableFilter.filter(table)) {
                    getLog().info("JSQLDSL: " + table.tableName());
                    dbDriver.createDSLTable(generatorTargetRoot, configuration.getTargetPackageName(), table);
                }
            }
        }
    }
}
