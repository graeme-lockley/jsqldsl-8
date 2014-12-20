package stuff;

public abstract class IntegerOperation implements IntegerType {
    public BooleanOperation eq(IntegerType integerType) {
        return new BooleanEqualsOperator(this, integerType);
    }

    public BooleanOperation eq(int constantInt) {
        return new BooleanEqualsOperator(this, constantInt);
    }

    public NestedQuery1<IntegerType> in() {
        return new NestedQuery1<>(this);
    }
}
