package za.co.no9.jsqldsl.db.h2;

public class BOOLEANColumnReference extends BOOLEANOperations implements ColumnReference, BOOLEANType {
    private final TableReference table;
    private final String name;

    private BOOLEANColumnReference(TableReference table, String name) {
        this.table = table;
        this.name = name;
    }

    public static BOOLEANColumnReference from(TableReference table, String name) {
        return new BOOLEANColumnReference(table, name);
    }

    @Override
    public String asString(Precedence precedence) {
        return table.reference() + '.' + name;
    }

}
