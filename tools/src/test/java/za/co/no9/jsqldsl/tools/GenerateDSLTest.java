package za.co.no9.jsqldsl.tools;

import org.junit.Test;
import za.co.no9.jfixture.Fixtures;
import za.co.no9.jfixture.FixturesInput;
import za.co.no9.jfixture.JDBCHandler;

import java.sql.Connection;

public class GenerateDSLTest {
    @Test
    public void should_generate_for_loaded_tables() throws Exception {
        Fixtures fixtures = Fixtures.load(FixturesInput.fromResources("shop.yaml"));
        fixtures.processFixtures();
        Connection connection = fixtures.findHandler(JDBCHandler.class).get().connection();

        for (TableMetaData tableMetaData : DatabaseMetaData.from(connection).tables("PUBLIC", null)) {
            new GenerateDSL().generateTable("a.b.c", tableMetaData);
        }
    }
}