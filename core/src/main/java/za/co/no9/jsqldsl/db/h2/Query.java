package za.co.no9.jsqldsl.db.h2;

public class Query {
    static <T1 extends TableRef> Query1<T1> from(T1 tableRef) {
        return new Query1<T1>(tableRef);
    }
}
