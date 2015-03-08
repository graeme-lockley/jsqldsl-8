package za.co.no9.jsqldsl.db.h2;

import za.co.no9.jsqldsl.db.*;

public class BIGINTColumnReference extends NumericOperations implements ColumnReference, NumericType {
    private final TableReference table;
    private final String name;

    protected BIGINTColumnReference(TableReference table, String name) {
        this.table = table;
        this.name = name;
    }

    public static BIGINTColumnReference from(TableReference table, String name) {
        return new BIGINTColumnReference(table, name);
    }

    @Override
    public String asString(Precedence precedence) {
        return table.reference() + '.' + name;
    }
}
