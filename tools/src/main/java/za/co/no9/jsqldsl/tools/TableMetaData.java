package za.co.no9.jsqldsl.tools;

import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TableMetaData {
    private TableName tableName;
    private FieldMetaData[] fieldsMetaData;

    private TableMetaData(TableName tableName, FieldMetaData[] fieldsMetaData) {
        this.tableName = tableName;
        this.fieldsMetaData = fieldsMetaData;
    }

    public static TableMetaData from(Connection connection, TableName tableName) throws SQLException {
        Set<String> primaryKey = primaryKey(connection, tableName);

        return new TableMetaData(tableName, fields(connection, tableName, primaryKey));
    }

    private static Set<String> primaryKey(Connection connection, TableName tableName) throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();

        Set<String> primaryKey = new HashSet<>();
        try (ResultSet resultSet = dbm.getPrimaryKeys("", "", tableName.dbName())) {
            while (resultSet.next()) {
                primaryKey.add(resultSet.getString(4));
            }
        }

        return primaryKey;
    }

    private static FieldMetaData[] fields(Connection connection, TableName tableName, Set<String> primaryKeys) throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();

        List<FieldMetaData> fields = new ArrayList<>();
        try (ResultSet resultSet = dbm.getColumns(tableName.catalog().orElse(""), tableName.schema().orElse(""), tableName.dbName(), null)) {
            while (resultSet.next()) {
                fields.add(fromColumnsResultSet(primaryKeys, resultSet));
            }
        }

        return fields.toArray(new FieldMetaData[1]);
    }

    protected static FieldMetaData fromColumnsResultSet(Set<String> primaryKeys, ResultSet resultSet) throws SQLException {
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

    public TableName tableName() {
        return tableName;
    }

    public Set<String> primaryKeyFieldNames() {
        Set<String> result = new HashSet<>();

        for (FieldMetaData fieldMetaData : fieldsMetaData) {
            if (fieldMetaData.isPrimaryKey()) {
                result.add(fieldMetaData.name());
            }
        }

        return result;
    }

    public Set<String> autoIncrementFieldNames() {
        Set<String> result = new HashSet<>();

        for (FieldMetaData fieldMetaData : fieldsMetaData) {
            if (fieldMetaData.isAutoIncrement()) {
                result.add(fieldMetaData.name());
            }
        }

        return result;
    }

    public List<FieldMetaData> fields() {
        return Arrays.asList(fieldsMetaData);
    }
}
