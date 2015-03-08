package za.co.no9.jsqldsl.db;

public class NumericConstant extends NumericOperations implements NumericType {
    private final int value;

    private NumericConstant(int value) {
        this.value = value;
    }

    public static NumericConstant from(int value) {
        return new NumericConstant(value);
    }

    @Override
    public String asString(Precedence precedence) {
        return Integer.toString(value);
    }
}
