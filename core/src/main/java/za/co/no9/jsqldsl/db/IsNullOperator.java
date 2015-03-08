package za.co.no9.jsqldsl.db;

public class IsNullOperator<T extends BaseType> extends BooleanOperations implements BooleanType {
    private T value;

    public IsNullOperator(T value) {
        this.value = value;
    }

    @Override
    public String asString(Precedence precedence) {
        return Precedence.IS_NULL.asString(value, v -> v + " IS NULL");
    }
}
