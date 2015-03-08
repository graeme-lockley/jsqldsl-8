package za.co.no9.jsqldsl.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampConstant extends TimestampOperations implements TimestampType {
    public static final String DATETIME_CONSTANT_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
    private java.util.Date value;

    private TimestampConstant(Date value) {
        if (value == null) {
            throw new NullPointerException("Value passed may not be null.");
        }

        this.value = value;
    }

    public static TimestampConstant from(Date value) {
        return new TimestampConstant(value);
    }

    public static TimestampConstant from(String value) throws ParseException {
        return new TimestampConstant(new SimpleDateFormat(DATETIME_CONSTANT_FORMAT).parse(value));
    }

    @Override
    public String asString(Precedence precedence) {
        return '\'' + new SimpleDateFormat(DATETIME_CONSTANT_FORMAT).format(value) + '\'';
    }
}
