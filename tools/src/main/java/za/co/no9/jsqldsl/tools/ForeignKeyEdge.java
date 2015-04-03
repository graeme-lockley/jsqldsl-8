package za.co.no9.jsqldsl.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ForeignKeyEdge {
    private final Optional<String> name;
    private final TableName tableName;
    private final Collection<String> columnNames;

    private ForeignKeyEdge(Optional<String> name, TableName tableName, Collection<String> columnNames) {
        this.name = name;
        this.tableName = tableName;
        this.columnNames = columnNames;
    }

    public static ForeignKeyEdge from(Optional<String> name, TableName tableName, Collection<String> columnNames) {
        return new ForeignKeyEdge(name, tableName, columnNames);
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
