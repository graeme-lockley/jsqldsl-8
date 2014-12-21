package za.co.no9.jsqldsl.db.h2;

public abstract class BOOLEANOperations implements BOOLEANType {
    public BOOLEANOperations eq(boolean constantBOOLEAN) {
        return new EQOperator<>(this, BOOLEANConstant.from(constantBOOLEAN));
    }

    public BOOLEANOperations eq(BOOLEANType value) {
        return new EQOperator<>(this, value);
    }

    public BOOLEANOperations noteq(boolean constantBOOLEAN) {
        return new NOTEQOperator<>(this, BOOLEANConstant.from(constantBOOLEAN));
    }

    public BOOLEANOperations noteq(BOOLEANType value) {
        return new NOTEQOperator<>(this, value);
    }

    public BOOLEANOperations and(boolean constantBOOLEAN) {
        return new ANDOperator(this, BOOLEANConstant.from(constantBOOLEAN));
    }

    public BOOLEANOperations and(BOOLEANType value) {
        return new ANDOperator(this, value);
    }

    public BOOLEANOperations or(boolean constantBOOLEAN) {
        return new OROperator(this, BOOLEANConstant.from(constantBOOLEAN));
    }

    public BOOLEANOperations or(BOOLEANType value) {
        return new OROperator(this, value);
    }

    public BOOLEANOperations not() {
        return new NOTOperator(this);
    }
}
