package za.co.no9.jsqldsl.db;

public class CurtimeFunction extends TimestampOperations {
    @Override
    public String asString(Precedence precedence) {
        return "CURTIME()";
    }}
