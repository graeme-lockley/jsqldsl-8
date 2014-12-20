package stuff;

public class NestedQuery1<BT extends BaseType> {
    private BT left;

    public NestedQuery1(BT left) {
        this.left = left;
    }

    public <T1 extends TableRef> NestedQuery1_1<BT, T1> from(T1 tableRef) {
        return new NestedQuery1_1<>(left, tableRef);
    }
}
