package za.co.no9.jsqldsl.db;

public abstract class StringOperations implements StringType {
    public BooleanOperations eq(String stringConstant) {
        return new EQOperator<>(this, StringConstant.from(stringConstant));
    }

    public BooleanOperations eq(StringType value) {
        return new EQOperator<>(this, value);
    }

    public BooleanOperations noteq(String stringConstant) {
        return new NOTEQOperator<>(this, StringConstant.from(stringConstant));
    }

    public BooleanOperations noteq(StringType value) {
        return new NOTEQOperator<>(this, value);
    }

    public BooleanOperations lt(String stringConstant) {
        return new LTOperator<>(this, StringConstant.from(stringConstant));
    }

    public BooleanOperations lt(NumericType value) {
        return new LTOperator<>(this, value);
    }

    public BooleanOperations le(String stringConstant) {
        return new LEOperator<>(this, StringConstant.from(stringConstant));
    }

    public BooleanOperations le(StringType value) {
        return new LEOperator<>(this, value);
    }

    public BooleanOperations gt(String stringConstant) {
        return new GTOperator<>(this, StringConstant.from(stringConstant));
    }

    public BooleanOperations gt(StringType value) {
        return new GTOperator<>(this, value);
    }

    public BooleanOperations ge(String stringConstant) {
        return new GEOperator<>(this, StringConstant.from(stringConstant));
    }

    public BooleanOperations ge(StringType value) {
        return new GEOperator<>(this, value);
    }

    public BooleanOperations isNull() {
        return new IsNullOperator<>(this);
    }

    public BooleanOperations isNotNull() {
        return new IsNotNullOperator<>(this);
    }
}
