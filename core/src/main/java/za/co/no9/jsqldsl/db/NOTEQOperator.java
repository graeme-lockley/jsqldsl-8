package za.co.no9.jsqldsl.db;

public class NOTEQOperator<T extends BaseType> extends BooleanOperations implements BooleanType {
    private final T left;
    private final T right;

    public NOTEQOperator(T left, T right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String asString(Precedence precedence) {
        return Precedence.NOTEQ_OPERATOR.asString(precedence, left, right, (l, r) -> l + " <> " + r);
    }
}
