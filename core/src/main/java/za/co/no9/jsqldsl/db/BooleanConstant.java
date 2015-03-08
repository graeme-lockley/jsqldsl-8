package za.co.no9.jsqldsl.db;

public class BooleanConstant extends BooleanOperations implements BooleanType {
    private final boolean value;

    private BooleanConstant(boolean value) {
        this.value = value;
    }

    public static BooleanConstant from(boolean value) {
        return new BooleanConstant(value);
    }

    @Override
    public String asString(Precedence precedence) {
        return value ? "TRUE" : "FALSE";
    }
}
