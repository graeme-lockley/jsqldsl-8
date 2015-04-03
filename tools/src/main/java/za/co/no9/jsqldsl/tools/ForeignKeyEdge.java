package za.co.no9.jsqldsl.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ForeignKeyEdge {
    private final Optional<String> name;
    private final TableName tableName;
    private final Collection<FieldMetaData> columnNames;

    private ForeignKeyEdge(Optional<String> name, TableName tableName, Collection<FieldMetaData> columnNames) {
        this.name = name;
        this.tableName = tableName;
        this.columnNames = columnNames;
    }

    public static ForeignKeyEdge from(Optional<String> name, TableName tableName, Collection<FieldMetaData> columnNames) {
        return new ForeignKeyEdge(name, tableName, columnNames);
    }

    public ForeignKeyEdge addColumn(FieldMetaData column) {
        List<FieldMetaData> newColumns = new ArrayList<>(columnNames);
        newColumns.add(column);

        return new ForeignKeyEdge(name, tableName, newColumns);
    }

    public FieldMetaData[] columns() {
        return columnNames.toArray(new FieldMetaData[columnNames.size()]);
    }

    public Optional<String> name() {
        return name;
    }

    public TableName tableName() {
        return tableName;
    }
}
