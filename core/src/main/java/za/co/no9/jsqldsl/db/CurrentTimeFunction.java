package za.co.no9.jsqldsl.db;

public class CurrentTimeFunction extends TimestampOperations {
    @Override
    public String asString(Precedence precedence) {
        return "CURRENT_TIME()";
    }
}
