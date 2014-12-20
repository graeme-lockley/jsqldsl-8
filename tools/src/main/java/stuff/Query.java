package stuff;

public class Query {
    static <T1 extends TableRef> Query1<T1> from(T1 tableRef) {
        return new Query1<T1>(tableRef);
    }

    static <T1 extends TableRef, T2 extends TableRef> Query2<T1, T2> from(T1 tableRef1, T2 tableRef2) {
        return new Query2<T1, T2>(tableRef1, tableRef2);
    }
}
