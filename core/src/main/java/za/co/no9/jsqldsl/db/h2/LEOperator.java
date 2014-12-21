package za.co.no9.jsqldsl.db.h2;

public class LEOperator<T extends BaseType> extends BOOLEANOperations implements BOOLEANType {
    private final T left;
    private final T right;

    public LEOperator(T left, T right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String asString(Precedence precedence) {
        return Precedence.LE_OPERATOR.asString(precedence, left, right, (l, r) -> l + " <= " + r);
    }
}