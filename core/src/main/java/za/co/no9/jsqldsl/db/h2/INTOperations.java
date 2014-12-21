package za.co.no9.jsqldsl.db.h2;

public abstract class INTOperations implements INTType {
    public BOOLEANOperations eq(int constantInt) {
        return new EQOperator<>(this, INTConstant.from(constantInt));
    }

    public BOOLEANOperations eq(INTType value) {
        return new EQOperator<>(this, value);
    }

    public BOOLEANOperations noteq(int constantInt) {
        return new NOTEQOperator<>(this, INTConstant.from(constantInt));
    }

    public BOOLEANOperations noteq(INTType value) {
        return new NOTEQOperator<>(this, value);
    }

    public BOOLEANOperations lt(int constantInt) {
        return new LTOperator<>(this, INTConstant.from(constantInt));
    }

    public BOOLEANOperations lt(INTType value) {
        return new LTOperator<>(this, value);
    }

    public BOOLEANOperations le(int constantInt) {
        return new LEOperator<>(this, INTConstant.from(constantInt));
    }

    public BOOLEANOperations le(INTType value) {
        return new LEOperator<>(this, value);
    }

    public BOOLEANOperations gt(int constantInt) {
        return new GTOperator<>(this, INTConstant.from(constantInt));
    }

    public BOOLEANOperations gt(INTType value) {
        return new GTOperator<>(this, value);
    }

    public BOOLEANOperations ge(int constantInt) {
        return new GEOperator<>(this, INTConstant.from(constantInt));
    }

    public BOOLEANOperations ge(INTType value) {
        return new GEOperator<>(this, value);
    }
}
