package stuff;

import java.util.Optional;
import java.util.function.Function;

public class NestedQuery1_1<BT1 extends BaseType, T1 extends TableRef> {
    private final BT1 left;
    private final T1 t1;
    private final Optional<BooleanType> where;

    public NestedQuery1_1(BT1 left, T1 t1) {
        this(left, t1, Optional.<BooleanType>empty());
    }

    private NestedQuery1_1(BT1 left, T1 t1, Optional<BooleanType> where) {
        this.left = left;
        this.t1 = t1;
        this.where = where;
    }

    public NestedQuery1_1<BT1, T1> where(Function<T1, BooleanType> whereClause) {
        return new NestedQuery1_1<>(left, t1, Optional.of(whereClause.apply(t1)));
    }

    public <T2 extends TableRef> OnQuery2<T1, T2> join(T2 table2) {
        return new OnQuery2<>(t1, table2);
    }

    public BooleanInOperator select(Function<T1, BT1> projection) {
        return new
                BooleanInOperator(left,
                new TupleRecord1<>(this, projection.apply(t1)));
    }

    public String asString() {
        return "FROM " + t1.asString() + (where.isPresent() ? (" WHERE " + where.get().asString(0)) : "");
    }
}
