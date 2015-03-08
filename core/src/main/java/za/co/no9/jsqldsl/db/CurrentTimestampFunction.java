package za.co.no9.jsqldsl.db;

import java.util.Optional;

public class CurrentTimestampFunction extends TimestampOperations {
    private final Optional<NumericType> precision;

    public CurrentTimestampFunction(Optional<NumericType> precision) {
        this.precision = precision;
    }

    @Override
    public String asString(Precedence precedence) {
        return precision.isPresent() ? "CURRENT_TIMESTAMP(" + precision.get().asString(Precedence.LOWEST_PRECEDENCE) + ")" : "CURRENT_TIMESTAMP()";
    }
}
