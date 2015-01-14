package za.co.no9.jsqldsl.db.h2;

public abstract class NumericOperations implements NumericType {
    public BOOLEANOperations eq(int intConstant) {
        return new EQOperator<>(this, NumericConstant.from(intConstant));
    }

    public BOOLEANOperations eq(NumericType value) {
        return new EQOperator<>(this, value);
    }

    public BOOLEANOperations noteq(int intConstant) {
        return new NOTEQOperator<>(this, NumericConstant.from(intConstant));
    }

    public BOOLEANOperations noteq(NumericType value) {
        return new NOTEQOperator<>(this, value);
    }

    public BOOLEANOperations lt(int intConstant) {
        return new LTOperator<>(this, NumericConstant.from(intConstant));
    }

    public BOOLEANOperations lt(NumericType value) {
        return new LTOperator<>(this, value);
    }

    public BOOLEANOperations le(int intConstant) {
        return new LEOperator<>(this, NumericConstant.from(intConstant));
    }

    public BOOLEANOperations le(NumericType value) {
        return new LEOperator<>(this, value);
    }

    public BOOLEANOperations gt(int intConstant) {
        return new GTOperator<>(this, NumericConstant.from(intConstant));
    }

    public BOOLEANOperations gt(NumericType value) {
        return new GTOperator<>(this, value);
    }

    public BOOLEANOperations ge(int intConstant) {
        return new GEOperator<>(this, NumericConstant.from(intConstant));
    }

    public BOOLEANOperations ge(NumericType value) {
        return new GEOperator<>(this, value);
    }
}
