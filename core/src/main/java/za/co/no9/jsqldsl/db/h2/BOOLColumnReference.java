package za.co.no9.jsqldsl.db.h2;

public class BOOLColumnReference extends BOOLEANColumnReference {
    protected BOOLColumnReference(TableReference table, String name) {
        super(table, name);
    }

    public static BOOLColumnReference from(TableReference table, String name) {
        return new BOOLColumnReference(table, name);
    }
}
