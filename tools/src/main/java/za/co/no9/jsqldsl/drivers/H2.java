package za.co.no9.jsqldsl.drivers;

import za.co.no9.jsqldsl.tools.*;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class H2 implements DBDriver {
    @Override
    public DatabaseMetaData databaseMetaData(Connection connection) {
        return DatabaseMetaData.from(this, connection);
    }

    @Override
    public TableMetaData tableMetaData(Connection connection, TableName tableName) throws SQLException {
        return TableMetaData.from(connection, tableName);
    }

    @Override
    public void createDSLTable(File generatorTargetRoot, String packageName, TableMetaData tableMetaData) throws GenerationException {
        new GenerateDSL().generateTable(generatorTargetRoot, packageName, tableMetaData);
    }
}
