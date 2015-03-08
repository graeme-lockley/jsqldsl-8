package za.co.no9.jsqldsl.db.h2;

import za.co.no9.jsqldsl.db.TableReference;

import java.util.Optional;

public class TABLE_A extends TableReference {
    private static final String NAME = "TABLE_A";

    public final INTColumnReference INT;
    public final BOOLEANColumnReference BOOLEAN;
    public final VARCHARColumnReference VARCHAR;
    public final TIMESTAMPColumnReference TIMESTAMP;

    TABLE_A(Optional<String> alias) {
        super(alias);

        INT = INTColumnReference.from(this, "INT");
        BOOLEAN = BOOLEANColumnReference.from(this, "BOOLEAN");
        VARCHAR = VARCHARColumnReference.from(this, "VARCHAR");
        TIMESTAMP = TIMESTAMPColumnReference.from(this, "TIMESTAMP");
    }

    public static TABLE_A ref() {
        return new TABLE_A(Optional.<String>empty());
    }

    public static TABLE_A as(String alias) {
        return new TABLE_A(Optional.of(alias));
    }

    @Override
    public String name() {
        return NAME;
    }
}
