package za.co.no9.jsqldsl.db.h2;

public class ANDOperator extends BOOLEANOperations implements BOOLEANType {
    private final BOOLEANType left;
    private final BOOLEANType right;

    public ANDOperator(BOOLEANType left, BOOLEANType right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String asString(Precedence precedence) {
        return Precedence.AND_OPERATOR.asString(precedence, left, right, (l, r) -> l + " AND " + r);
    }
}
