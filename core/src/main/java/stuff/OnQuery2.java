package stuff;

import java.util.function.BiFunction;

public class OnQuery2<T1 extends TableRef, T2 extends TableRef> {
    private T1 table1;
    private T2 table2;

    public OnQuery2(T1 table1, T2 table2) {
        this.table1 = table1;
        this.table2 = table2;
    }

    public Query2<T1, T2> on(BiFunction<T1, T2, BooleanType> onClause) {
        return new Query2<>(table1, table2, onClause.apply(table1, table2));
    }
}
