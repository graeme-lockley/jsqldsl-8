package za.co.no9.jsqldsl.db;

public class EQOperator<T extends BaseType> extends BooleanOperations implements BooleanType {
    private final BaseType left;
    private final BaseType right;

    public EQOperator(T left, T right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String asString(Precedence precedence) {
        return Precedence.EQ_OPERATOR.asString(precedence, left, right, (l, r) -> l + " = " + r);
    }
}
