package stuff;

class IntegerConstant implements IntegerType {
    private int value;

    IntegerConstant(int value) {
        this.value = value;
    }

    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public String asString(int precedence) {
        return Integer.toString(value);
    }
}
