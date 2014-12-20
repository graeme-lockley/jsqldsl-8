package stuff;

public class TupleRecord1<BT1 extends BaseType> implements BaseType {
    private final NestedQuery1_1<BT1, ?> query1;
    private final BT1 projection;

    public TupleRecord1(NestedQuery1_1<BT1, ?> query1, BT1 projection) {
        this.query1 = query1;
        this.projection = projection;
    }

    public String asString(int precedence) {
        return "(SELECT " + projection.asString(0) + " " + query1.asString() + ")";
    }
}
