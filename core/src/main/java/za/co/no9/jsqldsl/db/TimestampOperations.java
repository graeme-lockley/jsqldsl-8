package za.co.no9.jsqldsl.db;

import java.sql.Date;
import java.text.ParseException;

public abstract class TimestampOperations implements TimestampType {
    public BooleanOperations eq(String timestampConstant) throws ParseException {
        return new EQOperator<>(this, TimestampConstant.from(timestampConstant));
    }

    public BooleanOperations eq(Date timestampConstant) {
        return new EQOperator<>(this, TimestampConstant.from(timestampConstant));
    }

    public BooleanOperations eq(TimestampType value) {
        return new EQOperator<>(this, value);
    }

    public BooleanOperations noteq(String timestampConstant) throws ParseException {
        return new NOTEQOperator<>(this, TimestampConstant.from(timestampConstant));
    }

    public BooleanOperations noteq(Date timestampConstant) {
        return new NOTEQOperator<>(this, TimestampConstant.from(timestampConstant));
    }

    public BooleanOperations noteq(TimestampType value) {
        return new NOTEQOperator<>(this, value);
    }

    public BooleanOperations lt(String timestampConstant) throws ParseException {
        return new LTOperator<>(this, TimestampConstant.from(timestampConstant));
    }

    public BooleanOperations lt(Date timestampConstant) {
        return new LTOperator<>(this, TimestampConstant.from(timestampConstant));
    }

    public BooleanOperations lt(TimestampType value) {
        return new LTOperator<>(this, value);
    }

    public BooleanOperations le(String timestampConstant) throws ParseException {
        return new LEOperator<>(this, TimestampConstant.from(timestampConstant));
    }

    public BooleanOperations le(Date timestampConstant) {
        return new LEOperator<>(this, TimestampConstant.from(timestampConstant));
    }

    public BooleanOperations le(TimestampType value) {
        return new LEOperator<>(this, value);
    }

    public BooleanOperations gt(String timestampConstant) throws ParseException {
        return new GTOperator<>(this, TimestampConstant.from(timestampConstant));
    }

    public BooleanOperations gt(Date timestampConstant) {
        return new GTOperator<>(this, TimestampConstant.from(timestampConstant));
    }

    public BooleanOperations gt(TimestampType value) {
        return new GTOperator<>(this, value);
    }

    public BooleanOperations ge(String timestampConstant) throws ParseException {
        return new GEOperator<>(this, TimestampConstant.from(timestampConstant));
    }

    public BooleanOperations ge(Date timestampConstant) {
        return new GEOperator<>(this, TimestampConstant.from(timestampConstant));
    }

    public BooleanOperations ge(TimestampType value) {
        return new GEOperator<>(this, value);
    }

    public TimestampOperations dateAdd(String unit, int value) {
        return new DateAddFunction(StringConstant.from(unit), NumericConstant.from(value), this);
    }

    public TimestampOperations dateAdd(StringType unit, int value) {
        return new DateAddFunction(unit, NumericConstant.from(value), this);
    }

    public TimestampOperations dateAdd(String unit, NumericType value) {
        return new DateAddFunction(StringConstant.from(unit), value, this);
    }

    public TimestampOperations dateAdd(StringType unit, NumericType value) {
        return new DateAddFunction(unit, value, this);
    }

    public BooleanOperations isNull() {
        return new IsNullOperator<>(this);
    }

    public BooleanOperations isNotNull() {
        return new IsNotNullOperator<>(this);
    }
}
