package za.co.no9.jsqldsl.db.h2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueryTest {
    @Test
    public void should_return_all() throws Exception {
        assertEquals(
                "FROM TABLE_A AS a",
                Query.from(TABLE_A.as("a")).asString());
    }

    @Test
    public void should_support_where_clause() throws Exception {
        assertEquals(
                "FROM TABLE_A AS a WHERE a.INT = 100",
                Query.from(TABLE_A.as("a"))
                        .where(a -> a.INT.eq(100)).asString());
    }
}
