package za.co.no9.jsqldsl.db.h2;

public final class Query {
    private Query() {
    }

    public static <A extends TableReference> Query1<A> from(A tableRef) {
        return new Query1<>(tableRef);
    }
}
