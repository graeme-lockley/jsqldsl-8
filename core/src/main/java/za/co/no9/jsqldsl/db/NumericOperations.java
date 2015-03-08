package za.co.no9.jsqldsl.db;

public abstract class NumericOperations implements NumericType {
    public BooleanOperations eq(int intConstant) {
        return new EQOperator<>(this, NumericConstant.from(intConstant));
    }

    public BooleanOperations eq(NumericType value) {
        return new EQOperator<>(this, value);
    }

    public BooleanOperations noteq(int intConstant) {
        return new NOTEQOperator<>(this, NumericConstant.from(intConstant));
    }

    public BooleanOperations noteq(NumericType value) {
        return new NOTEQOperator<>(this, value);
    }

    public BooleanOperations lt(int intConstant) {
        return new LTOperator<>(this, NumericConstant.from(intConstant));
    }

    public BooleanOperations lt(NumericType value) {
        return new LTOperator<>(this, value);
    }

    public BooleanOperations le(int intConstant) {
        return new LEOperator<>(this, NumericConstant.from(intConstant));
    }

    public BooleanOperations le(NumericType value) {
        return new LEOperator<>(this, value);
    }

    public BooleanOperations gt(int intConstant) {
        return new GTOperator<>(this, NumericConstant.from(intConstant));
    }

    public BooleanOperations gt(NumericType value) {
        return new GTOperator<>(this, value);
    }

    public BooleanOperations ge(int intConstant) {
        return new GEOperator<>(this, NumericConstant.from(intConstant));
    }

    public BooleanOperations ge(NumericType value) {
        return new GEOperator<>(this, value);
    }

    public BooleanOperations isNull() {
        return new IsNullOperator<>(this);
    }

    public BooleanOperations isNotNull() {
        return new IsNotNullOperator<>(this);
    }
}
