package za.co.no9.jsqldsl.db.h2;

import org.junit.Test;

import static org.junit.Assert.*;
import static za.co.no9.jsqldsl.db.h2.INTConstant.from;
import static za.co.no9.jsqldsl.db.h2.Precedence.LOWEST_PRECEDENCE;

public class INTConstantTest {
    @Test
    public void should_format() throws Exception {
        assertEquals("123", from(123).asString(LOWEST_PRECEDENCE));
    }
}