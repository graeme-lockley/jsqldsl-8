package za.co.no9.jsqldsl.db.h2;

public abstract class INTOperations implements INTType {
    public BOOLEANType eq(int constantInt) {
        return new EQOperator<>(this, INTConstant.from(constantInt));
    }
}
