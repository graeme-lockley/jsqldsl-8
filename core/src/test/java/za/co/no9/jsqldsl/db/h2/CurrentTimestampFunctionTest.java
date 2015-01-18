package za.co.no9.jsqldsl.db.h2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static za.co.no9.jsqldsl.db.h2.DSL.CURRENT_TIMESTAMP;

public class CurrentTimestampFunctionTest {
    @Test
    public void should_marshal_with_no_argument() {
        assertEquals(
                "FROM TABLE_A AS A WHERE A.TIMESTAMP <= CURRENT_TIMESTAMP()",
                Query.from(TABLE_A.as("A"))
                        .where(a -> a.TIMESTAMP.le(CURRENT_TIMESTAMP())).asString());
    }

    @Test
    public void should_marshal_with_an_int_argument() {
        assertEquals(
                "FROM TABLE_A AS A WHERE A.TIMESTAMP <= CURRENT_TIMESTAMP(5)",
                Query.from(TABLE_A.as("A"))
                        .where(a -> a.TIMESTAMP.le(CURRENT_TIMESTAMP(5))).asString());
    }

    @Test
    public void should_marshal_with_a_numeric_expression_argument() {
        assertEquals(
                "FROM TABLE_A AS A WHERE A.TIMESTAMP <= CURRENT_TIMESTAMP(A.INT)",
                Query.from(TABLE_A.as("A"))
                        .where(a -> a.TIMESTAMP.le(CURRENT_TIMESTAMP(a.INT))).asString());
    }
}