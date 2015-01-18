package za.co.no9.jsqldsl.db.h2;

public class DSL {
    public static TimestampOperations CURRENT_TIME() {
        return new CurrentTimeFunction();
    }

    public static TimestampOperations CURRENT_TIMESTAMP() {
        return new CurrentTimestampFunction();
    }

    public static TimestampOperations CURRENT_TIMESTAMP(int precision) {
        return new CurrentTimestampFunction(precision);
    }

    public static TimestampOperations CURRENT_TIMESTAMP(NumericType precision) {
        return new CurrentTimestampFunction(precision);
    }
}
