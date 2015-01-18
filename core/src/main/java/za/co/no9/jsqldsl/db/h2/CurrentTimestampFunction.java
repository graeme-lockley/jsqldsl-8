package za.co.no9.jsqldsl.db.h2;

import java.util.Optional;

public class CurrentTimestampFunction extends TimestampOperations {
    private final Optional<NumericType> precision;

    public CurrentTimestampFunction() {
        this.precision = Optional.empty();
    }

    public CurrentTimestampFunction(int precision) {
        this(NumericConstant.from(precision));
    }

    public CurrentTimestampFunction(NumericType precision) {
        this.precision = Optional.of(precision);
    }

    @Override
    public String asString(Precedence precedence) {
        return precision.isPresent() ? ("CURRENT_TIMESTAMP(" + precision.get().asString(Precedence.LOWEST_PRECEDENCE) + ")") : "CURRENT_TIMESTAMP()";
    }
}
