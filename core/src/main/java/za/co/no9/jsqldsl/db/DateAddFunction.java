package za.co.no9.jsqldsl.db;

public class DateAddFunction extends TimestampOperations {
    private final StringType unit;
    private final NumericType value;
    private final TimestampType timestamp;

    public DateAddFunction(StringType unit, NumericType value, TimestampType timestamp) {
        this.unit = unit;
        this.value = value;
        this.timestamp = timestamp;
    }

    @Override
    public String asString(Precedence precedence) {
        return "DATEADD(" + unit.asString(Precedence.HIGHEST_PRECEDENCE) + ", " + value.asString(Precedence.HIGHEST_PRECEDENCE) + ", " + timestamp.asString(Precedence.HIGHEST_PRECEDENCE) + ")";
    }
}
