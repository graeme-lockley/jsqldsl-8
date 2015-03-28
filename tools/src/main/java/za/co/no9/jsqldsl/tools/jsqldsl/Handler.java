package za.co.no9.jsqldsl.tools.jsqldsl;

import org.apache.maven.plugin.logging.Log;
import za.co.no9.jsqldsl.drivers.DBDriver;
import za.co.no9.jsqldsl.port.jsqldslmojo.ConfigurationException;
import za.co.no9.jsqldsl.port.jsqldslmojo.TableFilter;
import za.co.no9.jsqldsl.port.jsqldslmojo.Target;
import za.co.no9.jsqldsl.tools.DatabaseMetaData;
import za.co.no9.jsqldsl.tools.GenerationException;
import za.co.no9.jsqldsl.tools.TableMetaData;
import za.co.no9.jsqldsl.tools.ToolHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class Handler implements ToolHandler {
    private Log log;
    private HandlerTarget target;

    @Override
    public void setup(Log log, Target target) {
        this.log = log;
        this.target = HandlerTarget.from(target);
    }

    @Override
    public void process(Connection connection, TableFilter tableFilter) throws SQLException, ConfigurationException, GenerationException {
        DBDriver dbDriver = target.getDBDriver();
        DatabaseMetaData databaseMetaData = dbDriver.databaseMetaData(connection);

        for (TableMetaData table : databaseMetaData.allTables()) {
            if (tableFilter.filter(table)) {
                log.info("JSQLDSL: " + table.tableName());
                dbDriver.createDSLTable(target.generatorTargetRoot(), target.getTargetPackageName(), table);
            }
        }

    }
}
