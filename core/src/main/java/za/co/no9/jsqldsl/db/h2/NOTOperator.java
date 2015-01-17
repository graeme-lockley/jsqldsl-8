package za.co.no9.jsqldsl.db.h2;

public class NOTOperator extends BooleanOperations implements BooleanType {
    private BooleanType value;

    public NOTOperator(BooleanType value) {
        super();
        this.value = value;
    }

    @Override
    public String asString(Precedence precedence) {
        return Precedence.NOT_OPERATOR.asString(precedence, value, v -> "NOT(" + v + ")");
    }
}
