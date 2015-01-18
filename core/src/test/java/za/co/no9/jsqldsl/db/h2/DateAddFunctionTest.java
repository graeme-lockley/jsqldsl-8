package za.co.no9.jsqldsl.db.h2;

import org.junit.Test;

import static org.junit.Assert.*;
import static za.co.no9.jsqldsl.db.h2.DSL.CURRENT_TIMESTAMP;

public class DateAddFunctionTest {
    @Test
    public void should_marshal_with_literal_literal_arguments() {
        assertEquals(
                "FROM TABLE_A AS A WHERE DATEADD('MONTH', 1, A.TIMESTAMP) <= CURRENT_TIMESTAMP()",
                Query.from(TABLE_A.as("A"))
                        .where(a -> a.TIMESTAMP.DATEADD("MONTH", 1).le(CURRENT_TIMESTAMP())).asString());
    }

    @Test
    public void should_marshal_with_expression_literal_arguments() {
        assertEquals(
                "FROM TABLE_A AS A WHERE DATEADD('MONTH', A.INT, A.TIMESTAMP) <= CURRENT_TIMESTAMP()",
                Query.from(TABLE_A.as("A"))
                        .where(a -> a.TIMESTAMP.DATEADD("MONTH", a.INT).le(CURRENT_TIMESTAMP())).asString());
    }

    @Test
    public void should_marshal_with_literal_expression_arguments() {
        assertEquals(
                "FROM TABLE_A AS A WHERE DATEADD(A.VARCHAR, 1, A.TIMESTAMP) <= CURRENT_TIMESTAMP()",
                Query.from(TABLE_A.as("A"))
                        .where(a -> a.TIMESTAMP.DATEADD(a.VARCHAR, 1).le(CURRENT_TIMESTAMP())).asString());
    }

    @Test
    public void should_marshal_with_expression_expression_arguments() {
        assertEquals(
                "FROM TABLE_A AS A WHERE DATEADD(A.VARCHAR, A.INT, A.TIMESTAMP) <= CURRENT_TIMESTAMP()",
                Query.from(TABLE_A.as("A"))
                        .where(a -> a.TIMESTAMP.DATEADD(a.VARCHAR, a.INT).le(CURRENT_TIMESTAMP())).asString());
    }
}