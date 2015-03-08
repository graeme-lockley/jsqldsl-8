package za.co.no9.jsqldsl.db;

public class IsNotNullOperator<T extends BaseType> extends BooleanOperations implements BooleanType {
    private T value;

    public IsNotNullOperator(T value) {
        this.value = value;
    }

    @Override
    public String asString(Precedence precedence) {
        return Precedence.IS_NOT_NULL.asString(value, v -> v + " IS NOT NULL");
    }
}