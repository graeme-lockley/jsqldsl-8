package stuff;

import java.util.List;

public class Record1<T1 extends TableRef> {
    private final Query1<T1> query1;
    private final List<BaseType> projections;

    public Record1(Query1<T1> query1, List<BaseType> projections) {
        this.query1 = query1;
        this.projections = projections;
    }

    public String asString() {
        StringBuilder sb = new StringBuilder();
        projections.forEach(x -> sb.append(", ").append(x.asString(0)));
        return "SELECT " + sb.substring(2) + " " + query1.asString();
    }
}
