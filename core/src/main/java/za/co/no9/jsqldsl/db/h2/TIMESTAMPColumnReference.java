package za.co.no9.jsqldsl.db.h2;

import za.co.no9.jsqldsl.db.*;

public class TIMESTAMPColumnReference extends TimestampOperations implements ColumnReference, TimestampType {
    private final TableReference table;
    private final String name;

    protected TIMESTAMPColumnReference(TableReference table, String name) {
        this.table = table;
        this.name = name;
    }

    public static TIMESTAMPColumnReference from(TableReference table, String name) {
        return new TIMESTAMPColumnReference(table, name);
    }

    @Override
    public String asString(Precedence precedence) {
        return table.reference() + '.' + name;
    }
}
