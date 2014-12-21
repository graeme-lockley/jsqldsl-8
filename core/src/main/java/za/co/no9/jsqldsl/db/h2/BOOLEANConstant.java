package za.co.no9.jsqldsl.db.h2;

public class BOOLEANConstant extends BOOLEANOperations implements BOOLEANType {
    private final boolean value;

    private BOOLEANConstant(boolean value) {
        this.value = value;
    }

    public static BOOLEANConstant from(boolean value) {
        return new BOOLEANConstant(value);
    }

    @Override
    public String asString(Precedence precedence) {
        return Boolean.toString(value);
    }
}
