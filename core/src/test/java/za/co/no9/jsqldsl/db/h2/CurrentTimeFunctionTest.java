package za.co.no9.jsqldsl.db.h2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static za.co.no9.jsqldsl.db.h2.DSL.CURRENT_TIME;

public class CurrentTimeFunctionTest {
    @Test
    public void should_marshal_with_no_arguments() {
        assertEquals(
                "FROM TABLE_A AS A WHERE A.TIMESTAMP <= CURRENT_TIME()",
                Query.from(TABLE_A.as("A"))
                        .where(a -> a.TIMESTAMP.le(CURRENT_TIME())).asString());
    }
}