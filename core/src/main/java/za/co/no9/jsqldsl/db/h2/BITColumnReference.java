package za.co.no9.jsqldsl.db.h2;

public class BITColumnReference extends BOOLEANColumnReference {
    protected BITColumnReference(TableReference table, String name) {
        super(table, name);
    }

    public static BITColumnReference from(TableReference table, String name) {
        return new BITColumnReference(table, name);
    }
}
