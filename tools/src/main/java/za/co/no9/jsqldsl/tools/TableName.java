package za.co.no9.jsqldsl.tools;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class TableName {
    private final Optional<String> catalog;
    private final Optional<String> schema;
    private final String tableName;

    private TableName(Optional<String> catalog, Optional<String> schema, String tableName) {
        this.catalog = catalog;
        this.schema = schema;
        this.tableName = tableName;
    }

    public Optional<String> catalog() {
        return catalog;
    }

    public Optional<String> schema() {
        return schema;
    }
    
    public String dbName() {
        return StringUtils.upperCase(tableName);
    }

    public static TableName from(String catalog, String schema, String name) {
        return new TableName(Optional.ofNullable(catalog), Optional.ofNullable(schema), name);
    }

    public String name() {
        return tableName;
    }

    @Override
    public String toString() {
        return catalog.orElse("") + "." + schema.orElse("") + "." + tableName;
    }
}
