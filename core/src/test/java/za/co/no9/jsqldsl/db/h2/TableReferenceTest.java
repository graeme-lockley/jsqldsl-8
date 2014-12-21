package za.co.no9.jsqldsl.db.h2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TableReferenceTest {
    @Test
    public void should_show_table_name() throws Exception {
        assertEquals("TABLE_A", TABLE_A.ref().asString());
    }

    @Test
    public void should_show_table_name_with_alias() throws Exception {
        assertEquals("TABLE_A AS x", TABLE_A.as("x").asString());
    }
}
