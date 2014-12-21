package za.co.no9.jsqldsl.db.h2;

public class INTColumnReference extends INTOperations implements ColumnReference, INTType {
    private final TableReference table;
    private final String name;

    private INTColumnReference(TableReference table, String name) {
        this.table = table;
        this.name = name;
    }

    public static INTColumnReference from(TableReference table, String name) {
        return new INTColumnReference(table, name);
    }

    @Override
    public String asString(Precedence precedence) {
        return table.reference() + '.' + name;
    }
}