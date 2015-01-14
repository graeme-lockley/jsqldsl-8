package za.co.no9.jsqldsl.db.h2;

public class INTEGERColumnReference extends INTColumnReference {
    protected INTEGERColumnReference(TableReference table, String name) {
        super(table, name);
    }

    public static INTEGERColumnReference from(TableReference table, String name) {
        return new INTEGERColumnReference(table, name);
    }
}
