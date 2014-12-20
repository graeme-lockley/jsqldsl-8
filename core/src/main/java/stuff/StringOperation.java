package stuff;

public abstract class StringOperation implements StringType {
    public BooleanType like(StringOperation stringOperation) {
        return new LikeOperator(this, stringOperation);
    }

    public BooleanOperand like(String constantString) {
        return new LikeOperator(this, new StringConstant(constantString));
    }

    public TrimOperation trim() {
        return new TrimOperation(this);
    }
}
