package stuff;

class OrOperator extends BooleanOperand implements BinaryOperator<BooleanType, BooleanType>, BooleanType {
    private BooleanType left;
    private BooleanType right;

    OrOperator(BooleanType left, BooleanType right) {
        this.left = left;
        this.right = right;
    }

    public String asString(int precedence) {
        if (precedence > 10) {
            return "(" + left.asString(10) + " OR " + right.asString(10) + ")";
        } else {
            return left.asString(10) + " OR " + right.asString(10);
        }
    }
}
