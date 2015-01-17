package za.co.no9.jsqldsl.db.h2;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TimestampConstantTest {
    public static final String CONSTANT_DATE_STRING = "1968-04-09 11:23:21.22";
    public static final Date CONSTANT_DATE = parseDate(CONSTANT_DATE_STRING);

    @Test
    public void should_be_able_to_parse_string() throws Exception {
        assertEquals("'" + CONSTANT_DATE_STRING + "'", TimestampConstant.from(CONSTANT_DATE_STRING).asString(Precedence.HIGHEST_PRECEDENCE));
    }

    @Test
    public void should_be_able_to_accept_dates() throws Exception {
        assertEquals("'" + CONSTANT_DATE_STRING + "'", TimestampConstant.from(CONSTANT_DATE).asString(Precedence.HIGHEST_PRECEDENCE));
    }

    private static Date parseDate(String dataAsString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(dataAsString);
        } catch (ParseException e) {
            return null;
        }
    }
}