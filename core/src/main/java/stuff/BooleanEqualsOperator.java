package stuff;

class BooleanEqualsOperator extends BooleanOperation {
    private IntegerType left;
    private IntegerType right;

    BooleanEqualsOperator(IntegerType left, int right) {
        this.left = left;
        this.right = new IntegerConstant(right);
    }

    BooleanEqualsOperator(IntegerType left, IntegerType right) {
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
