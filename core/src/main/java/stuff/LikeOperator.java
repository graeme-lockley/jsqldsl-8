package stuff;

class LikeOperator extends BooleanOperand implements BinaryOperator<StringType, StringType>, BooleanType {
    private StringType left;
    private StringType right;

    LikeOperator(StringType left, StringType right) {
        this.left = left;
        this.right = right;
    }

    LikeOperator(StringType left, String right) {
        this.left = left;
        this.right = new StringConstant(right);
    }

    public String toString() {
        return left.toString() + " LIKE " + right.toString();
    }

    @Override
    public String asString(int precedence) {
        if (precedence > 50) {
            return "(" + left.asString(50) + " LIKE " + right.asString(50) + ")";
        } else {
            return left.asString(50) + " LIKE " + right.asString(50);
        }
    }
}
