package za.co.no9.jsqldsl.db;

public class ANDOperator extends BooleanOperations implements BooleanType {
    private final BooleanType left;
    private final BooleanType right;

    public ANDOperator(BooleanType left, BooleanType right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String asString(Precedence precedence) {
        return Precedence.AND_OPERATOR.asString(precedence, left, right, (l, r) -> l + " AND " + r);
    }
}
