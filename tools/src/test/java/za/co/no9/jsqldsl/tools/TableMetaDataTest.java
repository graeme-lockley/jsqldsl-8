package za.co.no9.jsqldsl.tools;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import za.co.no9.jfixture.Fixtures;
import za.co.no9.jfixture.FixturesInput;
import za.co.no9.jfixture.JDBCHandler;
import za.co.no9.jsqldsl.drivers.H2;
import za.co.no9.jsqldsl.port.jsqldslmojo.TableFilter;
import za.co.no9.jsqldsl.port.jsqldslmojo.configuration.IncludeType;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TableMetaDataTest {
    private static Connection connection;

    @BeforeClass
    public static void beforeClass() throws Exception {
        Fixtures fixtures = Fixtures.load(FixturesInput.fromResources("foreign-key.yaml"));
        fixtures.processFixtures();
        connection = fixtures.findHandler(JDBCHandler.class).get().connection();
        connection.commit();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        connection.close();
    }

    @After
    public void after() throws Exception {
        connection.rollback();
    }

    @Test
    public void should_list_all_tables() throws Exception {
        IncludeType includeType = new IncludeType();
        includeType.setSchema("PUBLIC");
        TableFilter tableFilter = new TableFilter(Collections.singletonList(includeType));

        List<TableMetaData> tableMetaDatas = DatabaseMetaData.from(new H2(), connection).allTables().stream()
                .filter(tableFilter::filter)
                .collect(Collectors.toList());
        assertEquals(2, tableMetaDatas.size());
    }

    @Test
    public void should_confirm_books_content() throws Exception {
        IncludeType includeType = new IncludeType();
        includeType.setSchema("PUBLIC");
        includeType.setTable("BOOKS");
        TableFilter tableFilter = new TableFilter(Collections.singletonList(includeType));

        Optional<TableMetaData> optBookMetaData = DatabaseMetaData.from(new H2(), connection).allTables().stream()
                .filter(tableFilter::filter)
                .findFirst();
        assertTrue(optBookMetaData.isPresent());

        TableMetaData bookMetaData = optBookMetaData.get();
        assertEquals("BOOKS", bookMetaData.tableName().name());

        assertEquals(2, bookMetaData.foreignKeys().length);
        assertEquals("BOOKS_FK1", bookMetaData.foreignKeys()[0].fkName().get());
        assertEquals(TableName.from("UNNAMED", "PUBLIC", "AUTHORS"), bookMetaData.foreignKeys()[0].pkTableName());
        assertEquals(TableName.from("UNNAMED", "PUBLIC", "BOOKS"), bookMetaData.foreignKeys()[0].fkTableName());

        assertEquals("BOOKS_FK2", bookMetaData.foreignKeys()[1].fkName().get());
        assertEquals(TableName.from("UNNAMED", "PUBLIC", "AUTHORS"), bookMetaData.foreignKeys()[1].pkTableName());
        assertEquals(TableName.from("UNNAMED", "PUBLIC", "BOOKS"), bookMetaData.foreignKeys()[1].fkTableName());
    }
}