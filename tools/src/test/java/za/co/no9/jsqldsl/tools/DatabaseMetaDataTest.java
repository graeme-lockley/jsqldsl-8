package za.co.no9.jsqldsl.tools;

import org.junit.Test;
import za.co.no9.jfixture.Fixtures;
import za.co.no9.jfixture.FixturesInput;
import za.co.no9.jfixture.JDBCHandler;

import java.sql.Connection;

public class DatabaseMetaDataTest {
    @Test
    public void should_list_all_tables_in_database() throws Exception {
        Fixtures fixtures = Fixtures.load(FixturesInput.fromResources("shop.yaml"));
        fixtures.processFixtures();
        Connection connection = fixtures.findHandler(za.co.no9.jfixture.JDBCHandler.class).get().connection();

        for (TableMetaData tableMetaData : DatabaseMetaData.from(connection).tables("PUBLIC", null)) {
            System.out.println(tableMetaData.tableName());
            System.out.println(tableMetaData.fields());
        }
    }
}