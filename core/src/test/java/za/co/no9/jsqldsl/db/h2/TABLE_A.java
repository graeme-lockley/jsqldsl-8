package za.co.no9.jsqldsl.db.h2;

import java.util.Optional;

public class TABLE_A extends TableReference {
    private static final String NAME = "TABLE_A";
    public final INTColumnReference ID;

    TABLE_A(Optional<String> alias) {
        super(alias);

        ID = INTColumnReference.from(this, "ID");
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
