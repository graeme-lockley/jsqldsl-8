package za.co.no9.jsqldsl.db;

import java.util.Optional;

public class NowFunction extends TimestampOperations {
    private final Optional<NumericType> precision;

    public NowFunction() {
        this.precision = Optional.empty();
    }

    public NowFunction(int precision) {
        this(NumericConstant.from(precision));
    }

    public NowFunction(NumericType precision) {
        this.precision = Optional.of(precision);
    }

    @Override
    public String asString(Precedence precedence) {
        return precision.isPresent() ? "NOW(" + precision.get().asString(Precedence.LOWEST_PRECEDENCE) + ")" : "NOW()";
    }
}
