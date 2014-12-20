package stuff;

import org.junit.Test;

import java.util.Arrays;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class MainTest {
    @Test
    public void should_support_single_table_query() throws Exception {
        Query1<Author> query = Query.from(Author.as("A"))
                .where(a -> a.NAME.like("%LOCKLEY%"))
                .orderBy(a -> asList((OrderByExpression) a.NAME));

        assertEquals("FROM AUTHOR as A WHERE A.NAME LIKE '%LOCKLEY%'", query.asString());
    }

    @Test
    public void should_support_binary_table_query() throws Exception {
        Query2<Author, Book> query = Query.from(Author.as("A"), Book.as("B"))
                .where((a, b) ->
                        a.NAME.like("%LOCKLEY%")
                                .and(b.TITLE.like("%RINGS%")
                                        .or(b.TITLE.like("%HOBBIT%")))
                                .and(a.AUTHOR_ID.eq(b.AUTHOR_ID)))
                .orderBy((a, b) -> asList((OrderByExpression) a.NAME));

        assertEquals("FROM AUTHOR as A, BOOK as B WHERE A.NAME LIKE '%LOCKLEY%' AND (B.TITLE LIKE '%RINGS%' OR B.TITLE LIKE '%HOBBIT%') AND A.AUTHOR_ID = B.AUTHOR_ID", query.asString());
    }

    @Test
    public void should_single_table_query_with_join() throws Exception {
        Query2<Author, Book> query = Query.from(Author.as("A"))
                .join(Book.as("B"))
                .on((a, b) -> a.AUTHOR_ID.eq(b.AUTHOR_ID))
                .where((a, b) ->
                        a.NAME.like("%LOCKLEY%")
                                .and(b.TITLE.like("%RINGS%")
                                        .or(b.TITLE.like("%HOBBIT%"))))
                .orderBy((author, book) -> asList((OrderByExpression) author.NAME));

        assertEquals("FROM AUTHOR as A, BOOK as B WHERE A.AUTHOR_ID = B.AUTHOR_ID AND A.NAME LIKE '%LOCKLEY%' AND (B.TITLE LIKE '%RINGS%' OR B.TITLE LIKE '%HOBBIT%')", query.asString());
    }

    @Test
    public void should_return_a_full_query_across_a_single_table() {
        Record1<Author> query = Query.from(Author.as("A"))
                .where(a -> a.ID_NUMBER.eq(1234))
                .select(a -> asList(a.AUTHOR_ID, a.ID_NUMBER, a.NAME));

        assertEquals("SELECT A.AUTHOR_ID, A.ID_NUMBER, A.NAME FROM AUTHOR as A WHERE A.ID_NUMBER = 1234", query.asString());
    }

    @Test
    public void should_support_nested_sql_queries() throws Exception {
        Record1<Author> query = Query.from(Author.as("A"))
                .where(a -> a.AUTHOR_ID.in()
                        .from(Book.as("B"))
                        .where(b -> b.TITLE.like("%RINGS%"))
                        .select(b -> b.AUTHOR_ID))
                .select(a -> asList(a.AUTHOR_ID, a.ID_NUMBER, a.NAME));

        assertEquals("SELECT A.AUTHOR_ID, A.ID_NUMBER, A.NAME FROM AUTHOR as A WHERE A.AUTHOR_ID = (SELECT B.AUTHOR_ID FROM BOOK as B WHERE B.TITLE LIKE '%RINGS%')", query.asString());
    }
}