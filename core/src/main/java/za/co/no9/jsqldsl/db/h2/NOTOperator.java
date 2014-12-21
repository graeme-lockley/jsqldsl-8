package za.co.no9.jsqldsl.db.h2;

public class NOTOperator extends BOOLEANOperations implements BOOLEANType {
    private BOOLEANType value;

    public NOTOperator(BOOLEANType value) {
        super();
        this.value = value;
    }

    @Override
    public String asString(Precedence precedence) {
        return Precedence.NOT_OPERATOR.asString(precedence, value, v -> "NOT(" + v + ")");
    }
}
