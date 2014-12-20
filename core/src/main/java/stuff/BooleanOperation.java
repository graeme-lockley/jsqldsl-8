package stuff;

public abstract class BooleanOperation implements BooleanType {
    public BooleanType and(BooleanType booleanType) {
        return new AndOperator(this, booleanType);
    }

    public BooleanType or(BooleanType booleanType) {
        return new OrOperator(this, booleanType);
    }
}
