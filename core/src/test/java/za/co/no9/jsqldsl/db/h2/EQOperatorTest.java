package za.co.no9.jsqldsl.db.h2;

import org.junit.Test;

import static org.junit.Assert.*;
import static za.co.no9.jsqldsl.db.h2.INTConstant.from;

public class EQOperatorTest {
    @Test
    public void should_format_EQ_from_lower_precedence() throws Exception {
        assertEquals("1 = 2", new EQOperator(from(1), from(2)).asString(Precedence.LOWEST_PRECEDENCE));
    }

    @Test
    public void should_format_EQ_from_higher_precedence() throws Exception {
        assertEquals("(1 = 2)", new EQOperator(from(1), from(2)).asString(Precedence.HIGHEST_PRECEDENCE));
    }
}