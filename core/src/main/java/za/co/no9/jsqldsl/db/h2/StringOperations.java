package za.co.no9.jsqldsl.db.h2;

public abstract class StringOperations implements StringType {
    public BOOLEANOperations eq(String stringConstant) {
        return new EQOperator<>(this, StringConstant.from(stringConstant));
    }

    public BOOLEANOperations eq(StringType value) {
        return new EQOperator<>(this, value);
    }

    public BOOLEANOperations noteq(String stringConstant) {
        return new NOTEQOperator<>(this, StringConstant.from(stringConstant));
    }

    public BOOLEANOperations noteq(INTType value) {
        return new NOTEQOperator<>(this, value);
    }

    public BOOLEANOperations lt(String stringConstant) {
        return new LTOperator<>(this, StringConstant.from(stringConstant));
    }

    public BOOLEANOperations lt(INTType value) {
        return new LTOperator<>(this, value);
    }

    public BOOLEANOperations le(String stringConstant) {
        return new LEOperator<>(this, StringConstant.from(stringConstant));
    }

    public BOOLEANOperations le(INTType value) {
        return new LEOperator<>(this, value);
    }

    public BOOLEANOperations gt(String stringConstant) {
        return new GTOperator<>(this, StringConstant.from(stringConstant));
    }

    public BOOLEANOperations gt(INTType value) {
        return new GTOperator<>(this, value);
    }

    public BOOLEANOperations ge(String stringConstant) {
        return new GEOperator<>(this, StringConstant.from(stringConstant));
    }

    public BOOLEANOperations ge(INTType value) {
        return new GEOperator<>(this, value);
    }

}
