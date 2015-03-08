package za.co.no9.jsqldsl.db;

public abstract class BooleanOperations implements BooleanType {
    public BooleanOperations eq(boolean constantBOOLEAN) {
        return new EQOperator<>(this, BooleanConstant.from(constantBOOLEAN));
    }

    public BooleanOperations eq(BooleanType value) {
        return new EQOperator<>(this, value);
    }

    public BooleanOperations noteq(boolean constantBOOLEAN) {
        return new NOTEQOperator<>(this, BooleanConstant.from(constantBOOLEAN));
    }

    public BooleanOperations noteq(BooleanType value) {
        return new NOTEQOperator<>(this, value);
    }

    public BooleanOperations and(boolean constantBOOLEAN) {
        return new ANDOperator(this, BooleanConstant.from(constantBOOLEAN));
    }

    public BooleanOperations and(BooleanType value) {
        return new ANDOperator(this, value);
    }

    public BooleanOperations or(boolean constantBOOLEAN) {
        return new OROperator(this, BooleanConstant.from(constantBOOLEAN));
    }

    public BooleanOperations or(BooleanType value) {
        return new OROperator(this, value);
    }

    public BooleanOperations not() {
        return new NOTOperator(this);
    }

    public BooleanOperations isNull() {
        return new IsNullOperator<>(this);
    }

    public BooleanOperations isNotNull() {
        return new IsNotNullOperator<>(this);
    }
}
