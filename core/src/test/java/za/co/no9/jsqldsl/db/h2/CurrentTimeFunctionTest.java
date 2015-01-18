package za.co.no9.jsqldsl.db.h2;

import org.junit.Test;

import static org.junit.Assert.*;

public class CurrentTimeFunctionTest {
    @Test
    public void should_marshall_into_a_string() {
        assertEquals("CURRENT_TIME()", DSL.CURRENT_TIME().asString(Precedence.HIGHEST_PRECEDENCE));
    }
}