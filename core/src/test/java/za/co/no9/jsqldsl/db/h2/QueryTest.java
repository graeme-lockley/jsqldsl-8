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
}
