package za.co.no9.jsqldsl.db.h2;

public class EQOperator implements BOOLEANType {
    private final BaseType left;
    private final BaseType right;

    public EQOperator(BaseType left, BaseType right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String asString(Precedence precedence) {
        return Precedence.EQ_OPERATOR.asString(precedence, left, right, (l, r) -> l + " = " + r);
    }
}
