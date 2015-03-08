package za.co.no9.jsqldsl.db.h2;

import org.junit.Test;
import za.co.no9.jsqldsl.db.Query;

import static org.junit.Assert.assertEquals;

public class INTOperationsTest {
    @Test
    public void should_all_relational_INT_operators() throws Exception {
        assertEquals(
                "FROM TABLE_A AS a WHERE " +
                        "a.INT = 1 " +
                        "OR a.INT = a.INT " +
                        "OR a.INT <> 2 " +
                        "OR a.INT <> a.INT " +
                        "OR a.INT < 3 " +
                        "OR a.INT < a.INT " +
                        "OR a.INT <= 4 " +
                        "OR a.INT <= a.INT " +
                        "OR a.INT > 5 " +
                        "OR a.INT > a.INT " +
                        "OR a.INT >= 6 " +
                        "OR a.INT >= a.INT",
                Query.from(TABLE_A.as("a"))
                        .where(a -> a.INT.eq(1)
                                .or(a.INT.eq(a.INT))
                                .or(a.INT.noteq(2))
                                .or(a.INT.noteq(a.INT))
                                .or(a.INT.lt(3))
                                .or(a.INT.lt(a.INT))
                                .or(a.INT.le(4))
                                .or(a.INT.le(a.INT))
                                .or(a.INT.gt(5))
                                .or(a.INT.gt(a.INT))
                                .or(a.INT.ge(6))
                                .or(a.INT.ge(a.INT))).asString());
    }
}