package stuff;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

class Query1<T1 extends TableRef> {
    private final T1 t1;
    private final Optional<BooleanType> where;

    public Query1(T1 t1) {
        this(t1, Optional.<BooleanType>empty());
    }

    private Query1(T1 t1, Optional<BooleanType> where) {
        this.t1 = t1;
        this.where = where;
    }

    public Query1<T1> where(Function<T1, BooleanType> whereClause) {
        return new Query1<>(t1, Optional.of(whereClause.apply(t1)));
    }

    public Query1<T1> orderBy(Function<Author, List<OrderByExpression>> orderBy) {
        return this;
    }

    public <T2 extends TableRef> OnQuery2<T1, T2> join(T2 table2) {
        return new OnQuery2<>(t1, table2);
    }

    public Record1<T1> select(Function<T1, List<BaseType>> projections) {
        return new Record1<>(this, projections.apply(t1));
    }

    public String asString() {
        return "FROM " + t1.asString() + (where.isPresent() ? (" WHERE " + where.get().asString(0)) : "");
    }

}
