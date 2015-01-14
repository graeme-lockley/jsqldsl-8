package za.co.no9.jsqldsl.db.h2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static za.co.no9.jsqldsl.db.h2.Precedence.LOWEST_PRECEDENCE;
import static za.co.no9.jsqldsl.db.h2.StringConstant.from;

public class StringConstantTest {
    @Test
    public void should_format() throws Exception {
        assertEquals("'Hello'", from("Hello").asString(LOWEST_PRECEDENCE));
    }

    @Test
    public void should_markup_a_single_quote() throws Exception {
        assertEquals("'He''l''lo'", from("He'l'lo").asString(LOWEST_PRECEDENCE));
    }
}