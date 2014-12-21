package za.co.no9.jsqldsl.db.h2;

public class INTConstant extends INTOperations implements INTType {
    private final int value;

    private INTConstant(int value) {
        this.value = value;
    }

    public static INTConstant from(int value) {
        return new INTConstant(value);
    }

    @Override
    public String asString(Precedence precedence) {
        return Integer.toString(value);
    }
}
