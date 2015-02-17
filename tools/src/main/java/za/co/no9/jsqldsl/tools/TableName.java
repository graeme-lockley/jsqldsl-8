package za.co.no9.jsqldsl.tools;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class TableName {
    private final Optional<String> catalog;
    private final Optional<String> schema;
    private final String name;

    private TableName(Optional<String> catalog, Optional<String> schema, String name) {
        this.catalog = catalog;
        this.schema = schema;
        this.name = name;
    }

    public Optional<String> catalog() {
        return catalog;
    }

    public Optional<String> schema() {
        return schema;
    }
    
    public String dbName() {
        return StringUtils.upperCase(name);
    }

    public static TableName from(String catalog, String schema, String name) {
        return new TableName(Optional.ofNullable(catalog), Optional.ofNullable(schema), name);
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return catalog.orElse("") + "." + schema.orElse("") + "." + name;
    }
}
