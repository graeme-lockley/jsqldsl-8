package za.co.no9.jsqldsl.db.h2;

import org.junit.Test;
import za.co.no9.jsqldsl.db.Query;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class QueryTest {
    @Test
    public void should_return_all() throws Exception {
        assertEquals(
                "FROM TABLE_A AS a",
                Query.from(TABLE_A.as("a")).asString());
    }

    @Test
    public void should_support_select_with_where() throws Exception {
        assertEquals(
                "SELECT a.INT, a.BOOLEAN FROM TABLE_A AS a WHERE NOT(a.INT > 100 AND a.INT < 1000)",
                Query.from(TABLE_A.as("a"))
                        .where(a -> a.INT.gt(100).and(a.INT.lt(1000)).not())
                        .select(a -> asList(a.INT, a.BOOLEAN)).asString());
    }
}
