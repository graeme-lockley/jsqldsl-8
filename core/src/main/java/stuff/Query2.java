package stuff;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

public class Query2<T1 extends TableRef, T2 extends TableRef> {
    private final T1 t1;
    private final T2 t2;
    private final Optional<BooleanType> onClause;
    private final Optional<BooleanType> whereClause;

    public Query2(T1 t1, T2 t2) {
        this(t1, t2, Optional.<BooleanType>empty(), Optional.<BooleanType>empty());
    }

    public Query2(T1 t1, T2 t2, BooleanType onClause) {
        this(t1, t2, Optional.of(onClause), Optional.<BooleanType>empty());
    }

    private Query2(T1 t1, T2 t2, Optional<BooleanType> onClause, Optional<BooleanType> whereClause) {
        this.t1 = t1;
        this.t2 = t2;
        this.onClause = onClause;
        this.whereClause = whereClause;
    }

    public Query2<T1, T2> where(BiFunction<T1, T2, BooleanType> whereClauseFunction) {
        return new Query2<>(t1, t2, onClause, Optional.of(whereClauseFunction.apply(t1, t2)));
    }

    public Query2<T1, T2> orderBy(BiFunction<T1, T2, List<OrderByExpression>> orderBy) {
        return this;
    }

    public String asString() {
        return "FROM " + t1.asString() + ", " + t2.asString() +
                " WHERE " +
                onClause.map(x -> x.asString(0)).orElse("") +
                (onClause.isPresent() && whereClause.isPresent() ? " AND " : "") +
                whereClause.map(x -> x.asString(0)).orElse("");
    }
}
