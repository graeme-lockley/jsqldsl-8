package za.co.no9.jsqldsl.db.h2;

import java.util.Optional;
import java.util.function.Function;

public class Query1<T1 extends TableReference> {
    private final T1 t1;
    private final Optional<BOOLEANType> where;

    public Query1(T1 t1) {
        this(t1, Optional.<BOOLEANType>empty());
    }

    private Query1(T1 t1, Optional<BOOLEANType> where) {
        this.t1 = t1;
        this.where = where;
    }

    public Query1<T1> where(Function<T1, BOOLEANType> whereClause) {
        return new Query1<>(t1, Optional.of(whereClause.apply(t1)));
    }

//    public Query1<T1> orderBy(Function<Author, List<OrderByExpression>> orderBy) {
//        return this;
//    }

//    public <T2 extends stuff.TableRef> OnQuery2<T1, T2> join(T2 table2) {
//        return new OnQuery2<>(t1, table2);
//    }

//    public Record1<T1> select(Function<T1, List<BaseType>> projections) {
//        return new Record1<>(this, projections.apply(t1));
//    }

    public String asString() {
        return "FROM " + t1.asString() + (where.isPresent() ? (" WHERE " + where.get().asString(Precedence.QUERY_WHERE)) : "");
    }
}
