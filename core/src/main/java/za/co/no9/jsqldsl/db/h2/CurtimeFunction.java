package za.co.no9.jsqldsl.db.h2;

public class CurtimeFunction extends TimestampOperations {
    @Override
    public String asString(Precedence precedence) {
        return "CURTIME()";
    }}
