package za.co.no9.jsqldsl.tools;

import java.util.Optional;

public final class ForeignKey {
    private final ForeignKeyEdge primaryEdge;
    private final ForeignKeyEdge foreignEdge;

    private ForeignKey(ForeignKeyEdge primaryEdge, ForeignKeyEdge foreignEdge) {
        this.primaryEdge = primaryEdge;
        this.foreignEdge = foreignEdge;
    }

    public static ForeignKey from(ForeignKeyEdge primaryEdge, ForeignKeyEdge foreignEdge) {
        return new ForeignKey(primaryEdge, foreignEdge);
    }

    public Optional<String> pkName() {
        return primaryEdge.name();
    }

    public Optional<String> fkName() {
        return foreignEdge.name();
    }

    public TableName fkTableName() {
        return foreignEdge.tableName();
    }

    public TableName pkTableName() {
        return primaryEdge.tableName();
    }

    public ForeignKey addField(String pkColumnName, String fkColumnName) {
        return new ForeignKey(primaryEdge.addColumn(pkColumnName), foreignEdge.addColumn(fkColumnName));
    }

    public String[] pkColumnNames() {
        return primaryEdge.columnNames();
    }

    public String[] fkColumnNames() {
        return foreignEdge.columnNames();
    }
}

