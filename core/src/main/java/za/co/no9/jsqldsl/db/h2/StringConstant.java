package za.co.no9.jsqldsl.db.h2;

public class StringConstant extends StringOperations implements StringType {
    private final String value;

    private StringConstant(String value) {
        this.value = value;
    }

    public static StringConstant from(String value) {
        return new StringConstant(value);
    }

    @Override
    public String asString(Precedence precedence) {
        return '\'' + value + '\'';
    }
}
