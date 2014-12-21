package za.co.no9.jsqldsl.db.h2;

public abstract class INTOperations implements INTType {
    public BOOLEANType eq(int constantInt) {
        return new EQOperator<>(this, INTConstant.from(constantInt));
    }

    public BOOLEANType noteq(int constantInt) {
        return new NOTEQOperator<>(this, INTConstant.from(constantInt));
    }

    public BOOLEANType lt(int constantInt) {
        return new LTOperator<>(this, INTConstant.from(constantInt));
    }

    public BOOLEANType le(int constantInt) {
        return new LEOperator<>(this, INTConstant.from(constantInt));
    }

    public BOOLEANType gt(int constantInt) {
        return new GTOperator<>(this, INTConstant.from(constantInt));
    }

    public BOOLEANType ge(int constantInt) {
        return new GEOperator<>(this, INTConstant.from(constantInt));
    }
}
