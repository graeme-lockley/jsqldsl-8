package za.co.no9.jsqldsl.db;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public final class Query1<A extends TableReference> {
    private final A tableA;
    private final Optional<BooleanType> where;

    public Query1(A tableA) {
        this(tableA, Optional.<BooleanType>empty());
    }

    private Query1(A tableA, Optional<BooleanType> where) {
        this.tableA = tableA;
        this.where = where;
    }

    public Query1<A> where(Function<A, BooleanType> whereClause) {
        return new Query1<>(tableA, Optional.of(whereClause.apply(tableA)));
    }

//    public Query1<T1> orderBy(Function<Author, List<OrderByExpression>> orderBy) {
//        return this;
//    }

//    public <T2 extends stuff.TableRef> OnQuery2<T1, T2> join(T2 table2) {
//        return new OnQuery2<>(t1, table2);
//    }

    public Record1 select(Function<A, List<BaseType>> projections) {
        return new Record1<>(this, projections.apply(tableA));
    }

    public String asString() {
        return "FROM " + tableA.asString() + (where.isPresent() ? " WHERE " + where.get().asString(Precedence.QUERY_WHERE) : "");
    }
}
