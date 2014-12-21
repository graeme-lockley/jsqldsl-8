package za.co.no9.jsqldsl.db.h2;

public class OROperator extends BOOLEANOperations implements BOOLEANType {
    private final BaseType left;
    private final BOOLEANType right;

    public OROperator(BaseType left, BOOLEANType right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String asString(Precedence precedence) {
        return Precedence.OR_OPERATOR.asString(precedence, left, right, (l, r) -> l + " OR " + r);
    }
}
