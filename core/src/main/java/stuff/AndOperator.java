package stuff;

class AndOperator extends BooleanOperand implements BinaryOperator<BooleanType, BooleanType>, BooleanType {
    private BooleanType left;
    private BooleanType right;

    AndOperator(BooleanType left, BooleanType right) {
        this.left = left;
        this.right = right;
    }

    public String asString(int precedence) {
        if (precedence > 20) {
            return "(" + left.asString(20) + " AND " + right.asString(20) + ")";
        } else {
            return left.asString(20) + " AND " + right.asString(20);
        }
    }
}
