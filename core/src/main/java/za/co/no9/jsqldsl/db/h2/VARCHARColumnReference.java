package za.co.no9.jsqldsl.db.h2;

import za.co.no9.jsqldsl.db.*;

public class VARCHARColumnReference extends StringOperations implements ColumnReference, StringType {
    private final TableReference table;
    private final String name;

    protected VARCHARColumnReference(TableReference table, String name) {
        this.table = table;
        this.name = name;
    }

    public static VARCHARColumnReference from(TableReference table, String name) {
        return new VARCHARColumnReference(table, name);
    }

    @Override
    public String asString(Precedence precedence) {
        return table.reference() + '.' + name;
    }
}
