package za.co.no9.jsqldsl.db.h2;

import org.junit.Test;
import za.co.no9.jsqldsl.db.Query;

import static org.junit.Assert.assertEquals;

public class BOOLEANOperationsTest {
    @Test
    public void should_not_expression() throws Exception {
        assertEquals(
                "FROM TABLE_A AS a WHERE NOT(a.INT < 1000)",
                Query.from(TABLE_A.as("a"))
                        .where(a -> a.INT.lt(1000).not()).asString());
    }

    @Test
    public void should_and_with_expression() throws Exception {
        assertEquals(
                "FROM TABLE_A AS a WHERE a.BOOLEAN = TRUE AND TRUE AND a.INT < 1000 AND a.INT > 10",
                Query.from(TABLE_A.as("a"))
                        .where(a -> a.BOOLEAN.eq(true)
                                .and(true)
                                .and(a.INT.lt(1000))
                                .and(a.INT.gt(10))).asString());
    }

    @Test
    public void should_or_with_expression() throws Exception {
        assertEquals(
                "FROM TABLE_A AS a WHERE a.BOOLEAN = TRUE OR TRUE OR a.INT < 10 OR a.INT > 1000",
                Query.from(TABLE_A.as("a"))
                        .where(a -> a.BOOLEAN.eq(true)
                                .or(true)
                                .or(a.INT.lt(10))
                                .or(a.INT.gt(1000))).asString());
    }

    @Test
    public void should_eq_with_expression() throws Exception {
        assertEquals(
                "FROM TABLE_A AS a WHERE (a.BOOLEAN = TRUE OR a.INT < 10) = (a.INT > 1000) OR a.INT > 1000",
                Query.from(TABLE_A.as("a"))
                        .where(a -> a.BOOLEAN.eq(true)
                                .or(a.INT.lt(10))
                                .eq(a.INT.gt(1000))
                                .or(a.INT.gt(1000))).asString());
    }

    @Test
    public void should_noteq_with_expression() throws Exception {
        assertEquals(
                "FROM TABLE_A AS a WHERE (a.BOOLEAN <> TRUE OR a.INT < 10) <> (a.INT > 1000) OR a.INT > 1000",
                Query.from(TABLE_A.as("a"))
                        .where(a -> a.BOOLEAN.noteq(true)
                                .or(a.INT.lt(10)).noteq(a.INT.gt(1000))
                                .or(a.INT.gt(1000))).asString());
    }
}