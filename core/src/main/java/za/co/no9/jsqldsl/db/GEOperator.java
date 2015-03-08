package za.co.no9.jsqldsl.db;

public class GEOperator<T extends BaseType> extends BooleanOperations implements BooleanType {
    private final T left;
    private final T right;

    public GEOperator(T left, T right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String asString(Precedence precedence) {
        return Precedence.GE_OPERATOR.asString(precedence, left, right, (l, r) -> l + " >= " + r);
    }
}
