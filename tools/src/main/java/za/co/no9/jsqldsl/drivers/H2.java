package za.co.no9.jsqldsl.drivers;

import org.apache.commons.lang3.StringUtils;
import za.co.no9.jsqldsl.tools.*;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class H2 implements DBDriver {
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

    protected FieldMetaData fromColumnsResultSet(Set<String> primaryKeys, ResultSet resultSet) throws SQLException {
        String fieldName = resultSet.getString(4);
        String fieldType = resultSet.getString(6);
        Optional<Integer> columnSize = parseInt(resultSet.getString(7));
        Optional<Integer> subWidth = parseInt(resultSet.getString(9));
        boolean nullable = Integer.parseInt(resultSet.getString(11)) == 1;
        boolean isAutoIncrement = StringUtils.equalsIgnoreCase("YES", resultSet.getString(23));

        return new FieldMetaData(fieldName, fieldType, columnSize, subWidth, nullable, primaryKeys.contains(fieldName), isAutoIncrement);
    }

    private static Optional<Integer> parseInt(String intString) {
        try {
            return Optional.of(Integer.parseInt(intString));
        } catch (NullPointerException | NumberFormatException ignored) {
            return Optional.empty();
        }
    }

    @Override
    public void createDSLTable(File generatorTargetRoot, String packageName, TableMetaData tableMetaData) throws GenerationException {
        new GenerateDSL().generateTable(generatorTargetRoot, packageName, tableMetaData);
    }
}
