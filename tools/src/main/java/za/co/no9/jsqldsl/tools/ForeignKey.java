package za.co.no9.jsqldsl.tools;

import java.util.*;

public class ForeignKey {
    private final ForeignKeyEdge primaryKey;
    private final ForeignKeyEdge foreignKey;

    public ForeignKey(Optional<String> pkName, TableName pkTableName, String pkColumnName, Optional<String> fkName, TableName fkTableName, String fkColumnName) {
        this(new ForeignKeyEdge(pkName, pkTableName, Collections.singletonList(pkColumnName)), new ForeignKeyEdge(fkName, fkTableName, Collections.singletonList(fkColumnName)));
    }

    private ForeignKey(ForeignKeyEdge primaryKey, ForeignKeyEdge foreignKey) {
        this.primaryKey = primaryKey;
        this.foreignKey = foreignKey;
    }

    public Optional<String> pkName() {
        return primaryKey.name();
    }

    public Optional<String> fkName() {
        return foreignKey.name();
    }

    public TableName fkTableName() {
        return foreignKey.tableName();
    }

    public TableName pkTableName() {
        return primaryKey.tableName();
    }

    public ForeignKey addField(String pkColumnName, String fkColumnName) {
        return new ForeignKey(primaryKey.addColumn(pkColumnName), foreignKey.addColumn(fkColumnName));
    }

    public String[] pkColumnNames() {
        return primaryKey.columnNames();
    }

    public String[] fkColumnNames() {
        return foreignKey.columnNames();
    }
}

class ForeignKeyEdge {
    private final Optional<String> name;
    private final TableName tableName;
    private final Collection<String> columnNames;

    public ForeignKeyEdge(Optional<String> name, TableName tableName, Collection<String> columnNames) {
        this.name = name;
        this.tableName = tableName;
        this.columnNames = columnNames;
    }

    public ForeignKeyEdge addColumn(String columnName) {
        List<String> newColumns = new ArrayList<>(columnNames);
        newColumns.add(columnName);

        return new ForeignKeyEdge(name, tableName, newColumns);
    }

    public String[] columnNames() {
        return columnNames.toArray(new String[columnNames.size()]);
    }

    public Optional<String> name() {
        return name;
    }

    public TableName tableName() {
        return tableName;
    }
}
