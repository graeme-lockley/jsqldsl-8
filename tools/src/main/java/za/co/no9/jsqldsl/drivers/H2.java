package za.co.no9.jsqldsl.drivers;

import za.co.no9.jsqldsl.tools.DatabaseMetaData;
import za.co.no9.jsqldsl.tools.GenerateDSL;
import za.co.no9.jsqldsl.tools.GenerationException;
import za.co.no9.jsqldsl.tools.TableMetaData;

import java.io.File;
import java.sql.Connection;

public class H2 implements DBDriver {
    @Override
    public DatabaseMetaData databaseMetaData(Connection connection) {
        return DatabaseMetaData.from(this, connection);
    }

    @Override
    public void createDSLTable(File generatorTargetRoot, String packageName, TableMetaData tableMetaData) throws GenerationException {
        new GenerateDSL().generateTable(generatorTargetRoot, packageName, tableMetaData);
    }
}
