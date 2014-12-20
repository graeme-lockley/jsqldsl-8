package stuff;

public abstract class BooleanOperand implements BooleanType {
    public BooleanOperand and(BooleanType booleanType) {
        return new AndOperator(this, booleanType);
    }

    public BooleanOperand or(BooleanType booleanType) {
        return new OrOperator(this, booleanType);
    }
}
