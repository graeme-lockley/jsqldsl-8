package za.co.no9.jsqldsl.drivers;

import za.co.no9.jsqldsl.tools.*;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class DBDriverParent implements DBDriver {
    @Override
    public DatabaseMetaData databaseMetaData(Connection connection) {
        return DatabaseMetaData.from(this, connection);
    }

    @Override
    public TableMetaData tableMetaData(Connection connection, TableName tableName) throws SQLException {
        Set<String> primaryKey = primaryKey(connection, tableName);

        return new TableMetaData(tableName, fields(connection, tableName, primaryKey));
    }

    protected Set<String> primaryKey(Connection connection, TableName tableName) throws SQLException {
        java.sql.DatabaseMetaData dbm = connection.getMetaData();

        Set<String> primaryKey = new HashSet<>();
        try (ResultSet resultSet = dbm.getPrimaryKeys("", "", tableName.dbName())) {
            while (resultSet.next()) {
                primaryKey.add(resultSet.getString(4));
            }
        }

        return primaryKey;
    }

    private FieldMetaData[] fields(Connection connection, TableName tableName, Set<String> primaryKeys) throws SQLException {
        java.sql.DatabaseMetaData dbm = connection.getMetaData();

        List<FieldMetaData> fields = new ArrayList<>();
        try (ResultSet resultSet = dbm.getColumns(tableName.catalog().orElse(""), tableName.schema().orElse(""), tableName.dbName(), null)) {
            while (resultSet.next()) {
                fields.add(fromColumnsResultSet(primaryKeys, resultSet));
            }
        }

        return fields.toArray(new FieldMetaData[1]);
    }

    protected abstract FieldMetaData fromColumnsResultSet(Set<String> primaryKeys, ResultSet resultSet) throws SQLException;

    @Override
    public void createDSLTable(File generatorTargetRoot, String packageName, TableMetaData tableMetaData) throws GenerationException {
        new GenerateDSL().generateTable(generatorTargetRoot, packageName, tableMetaData);
    }
}
