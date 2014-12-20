package stuff;

public class BooleanInOperator <BT1 extends BaseType> extends BooleanOperation {
    private BT1 left;
    private BT1 right;

    BooleanInOperator(BT1 left, BT1 right) {
        this.left = left;
        this.right = right;
    }

    public String toString() {
        return left.toString() + " = " + right.toString();
    }

    @Override
    public String asString(int precedence) {
        if (precedence > 40) {
            return "(" + left.asString(40) + " = " + right.asString(40) + ")";
        } else {
            return left.asString(40) + " = " + right.asString(40);
        }
    }
}
