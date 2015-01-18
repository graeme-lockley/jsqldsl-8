package za.co.no9.jsqldsl.db.h2;

import java.util.Optional;

public class DSL {
    public static TimestampOperations CURRENT_TIME() {
        return new CurrentTimeFunction();
    }

    public static TimestampOperations CURTIME() {
        return new CurtimeFunction();
    }

    public static TimestampOperations CURRENT_TIMESTAMP() {
        return new CurrentTimestampFunction(Optional.<NumericType>empty());
    }

    public static TimestampOperations CURRENT_TIMESTAMP(int precision) {
        return new CurrentTimestampFunction(Optional.of(NumericConstant.from(precision)));
    }

    public static TimestampOperations CURRENT_TIMESTAMP(NumericType precision) {
        return new CurrentTimestampFunction(Optional.of(precision));
    }

    public static TimestampOperations NOW() {
        return new NowFunction();
    }

    public static TimestampOperations NOW(int precision) {
        return new NowFunction(precision);
    }

    public static TimestampOperations NOW(NumericType precision) {
        return new NowFunction(precision);
    }
}
