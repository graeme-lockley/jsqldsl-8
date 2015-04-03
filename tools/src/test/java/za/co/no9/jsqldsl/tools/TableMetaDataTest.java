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
import za.co.no9.jsqldsl.port.jsqldslmojo.configuration.TablePatternType;

import java.sql.Connection;
import java.util.Arrays;
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
        TablePatternType includeType = new TablePatternType();
        includeType.setSchema("PUBLIC");
        TableFilter tableFilter = new TableFilter(Collections.singletonList(includeType), Collections.EMPTY_LIST);

        List<TableMetaData> tableMetaDatas = DatabaseMetaData.from(new H2(), connection).allTables().stream()
                .filter(tableFilter::filter)
                .collect(Collectors.toList());
        assertEquals(4, tableMetaDatas.size());
    }

    @Test
    public void should_confirm_books_content() throws Exception {
        TablePatternType includeType = new TablePatternType();
        includeType.setSchema("PUBLIC");
        includeType.setTable("BOOKS");
        TableFilter tableFilter = new TableFilter(Collections.singletonList(includeType), Collections.EMPTY_LIST);

        Optional<TableMetaData> optBookMetaData = DatabaseMetaData.from(new H2(), connection).allTables().stream()
                .filter(tableFilter::filter)
                .findFirst();
        assertTrue(optBookMetaData.isPresent());

        TableMetaData bookMetaData = optBookMetaData.get();
        assertEquals("BOOKS", bookMetaData.tableName().name());

        assertEquals(2, bookMetaData.foreignKeys().length);
        assertConstraint(
                ForeignKey.from(
                        ForeignKeyEdge.from(Optional.of("BOOKS_FK1_INDEX_4"), TableName.from("UNNAMED", "PUBLIC", "AUTHORS"), Arrays.asList(fieldMetaData("ID"), fieldMetaData("FIRST_NAME"))),
                        ForeignKeyEdge.from(Optional.of("BOOKS_FK1"), TableName.from("UNNAMED", "PUBLIC", "BOOKS"), Arrays.asList(fieldMetaData("AUTHOR_ID"), fieldMetaData("NAME")))),
                bookMetaData.foreignKeys()[0]);
        assertConstraint(
                ForeignKey.from(
                        ForeignKeyEdge.from(Optional.of("BOOKS_FK2_INDEX_4"), TableName.from("UNNAMED", "PUBLIC", "AUTHORS"), Arrays.asList(fieldMetaData("ID"), fieldMetaData("SURNAME"))),
                        ForeignKeyEdge.from(Optional.of("BOOKS_FK2"), TableName.from("UNNAMED", "PUBLIC", "BOOKS"), Arrays.asList(fieldMetaData("AUTHOR_ID"), fieldMetaData("NAME")))),
                bookMetaData.foreignKeys()[1]);
    }

    private void assertConstraint(ForeignKey expected, ForeignKey actual) {
        assertEquals(expected.fkName(), actual.fkName());
        assertEquals(expected.pkName(), actual.pkName());
        assertEquals(expected.pkTableName(), actual.pkTableName());
        assertEquals(expected.pkColumns(), actual.pkColumns());
        assertEquals(expected.fkTableName(), actual.fkTableName());
        assertEquals(expected.fkColumns(), actual.fkColumns());
    }

    private FieldMetaData fieldMetaData(String name) {
        return new FieldMetaData(name, "", Optional.<Integer>empty(), Optional.<Integer>empty(), false, false, false);
    }
}