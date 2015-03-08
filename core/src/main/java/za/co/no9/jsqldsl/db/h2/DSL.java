package za.co.no9.jsqldsl.db.h2;

import za.co.no9.jsqldsl.db.*;

import java.util.Optional;

public final class DSL {
    private DSL() {
    }

    public static TimestampOperations currentTime() {
        return new CurrentTimeFunction();
    }

    public static TimestampOperations curTime() {
        return new CurtimeFunction();
    }

    public static TimestampOperations currentTimestamp() {
        return new CurrentTimestampFunction(Optional.<NumericType>empty());
    }

    public static TimestampOperations currentTimestamp(int precision) {
        return new CurrentTimestampFunction(Optional.of(NumericConstant.from(precision)));
    }

    public static TimestampOperations currentTimestamp(NumericType precision) {
        return new CurrentTimestampFunction(Optional.of(precision));
    }

    public static TimestampOperations now() {
        return new NowFunction();
    }

    public static TimestampOperations now(int precision) {
        return new NowFunction(precision);
    }

    public static TimestampOperations now(NumericType precision) {
        return new NowFunction(precision);
    }
}
