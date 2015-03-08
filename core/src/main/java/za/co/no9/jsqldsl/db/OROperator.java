package za.co.no9.jsqldsl.db;

public class OROperator extends BooleanOperations implements BooleanType {
    private final BaseType left;
    private final BooleanType right;

    public OROperator(BaseType left, BooleanType right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String asString(Precedence precedence) {
        return Precedence.OR_OPERATOR.asString(precedence, left, right, (l, r) -> l + " OR " + r);
    }
}
