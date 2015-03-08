package za.co.no9.jsqldsl.db;

import java.util.List;

public class Record1<A extends TableReference> {
    private final Query1<A> query1;
    private final List<BaseType> projections;

    public Record1(Query1<A> query1, List<BaseType> projections) {
        this.query1 = query1;
        this.projections = projections;
    }

    public String asString() {
        StringBuilder sb = new StringBuilder();
        projections.forEach(x -> sb.append(", ").append(x.asString(Precedence.LOWEST_PRECEDENCE)));
        return "SELECT " + sb.substring(2) + " " + query1.asString();
    }
}