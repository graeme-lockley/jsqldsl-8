package za.co.no9.jsqldsl.db.h2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static za.co.no9.jsqldsl.db.NumericConstant.from;
import static za.co.no9.jsqldsl.db.Precedence.LOWEST_PRECEDENCE;

public class NumericConstantTest {
    @Test
    public void should_format() throws Exception {
        assertEquals("123", from(123).asString(LOWEST_PRECEDENCE));
    }
}