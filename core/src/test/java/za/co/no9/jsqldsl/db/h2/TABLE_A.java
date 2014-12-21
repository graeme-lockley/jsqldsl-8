package za.co.no9.jsqldsl.db.h2;

public class TABLE_A extends TableRef {
    private static final String NAME = "TABLE_A";

    TABLE_A() {
        super();
    }

    TABLE_A(String alias) {
        super(alias);
    }

    public static TABLE_A ref() {
        return new TABLE_A();
    }

    public static TABLE_A as(String alias) {
        return new TABLE_A(alias);
    }

    @Override
    public String name() {
        return NAME;
    }
}
