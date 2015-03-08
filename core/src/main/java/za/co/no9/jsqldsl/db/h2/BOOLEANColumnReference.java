package za.co.no9.jsqldsl.db.h2;

import za.co.no9.jsqldsl.db.*;

public class BOOLEANColumnReference extends BooleanOperations implements ColumnReference, BooleanType {
    private final TableReference table;
    private final String name;

    protected BOOLEANColumnReference(TableReference table, String name) {
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
