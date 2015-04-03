package za.co.no9.jsqldsl.tools;

import za.co.no9.jsqldsl.drivers.DBDriver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DatabaseMetaData {
    private DBDriver dbDriver;
    private Connection connection;

    public DatabaseMetaData(DBDriver dbDriver, Connection connection) {
        this.dbDriver = dbDriver;
        this.connection = connection;
    }

    public static DatabaseMetaData from(DBDriver dbDriver, Connection connection) {
        return new DatabaseMetaData(dbDriver, connection);
    }

    public Collection<TableMetaData> allTables() throws SQLException {
        return tables(null, null);
    }

    private Collection<TableMetaData> tables(String schemaNamePattern, String tableNamePattern) throws SQLException {
        Map<TableName, TableMetaData> tables = new HashMap<>();

        java.sql.DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet rs = metaData.getTables(null, schemaNamePattern, tableNamePattern, null)) {
            while (rs.next()) {
                TableMetaData tableMetaData = dbDriver.tableMetaData(connection, TableName.from(rs.getString(1), rs.getString(2), rs.getString(3)));
                tables.put(tableMetaData.tableName(), tableMetaData);
            }
        }

        return tables.values();
    }
}
