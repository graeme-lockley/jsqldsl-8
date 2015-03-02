package za.co.no9.jsqldsl.tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseMetaData {
    private Connection connection;

    public DatabaseMetaData(Connection connection) {
        this.connection = connection;
    }

    public static DatabaseMetaData from(Connection connection) {
        return new DatabaseMetaData(connection);
    }

    public List<TableMetaData> allTables() throws SQLException {
        return tables(null, null);
    }

    private List<TableMetaData> tables(String schemaNamePattern, String tableNamePattern) throws SQLException {
        List<TableMetaData> tables = new ArrayList<>();

        java.sql.DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet rs = metaData.getTables(null, schemaNamePattern, tableNamePattern, null)) {
            while (rs.next()) {
                tables.add(TableMetaData.from(connection, TableName.from(rs.getString(1), rs.getString(2), rs.getString(3))));
            }
        }

        return tables;
    }
}
