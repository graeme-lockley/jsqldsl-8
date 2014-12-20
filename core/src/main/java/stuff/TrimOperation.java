package stuff;

public class TrimOperation extends StringOperation implements OrderByExpression {
    private final StringOperation item;

    public TrimOperation(StringOperation item) {
        this.item = item;
    }

    public String toString() {
        return "TRIM(" + item.toString() + ")";
    }

    @Override
    public String asString(int precedence) {
        return "TRIM(" + item.asString(0) + ")";
    }
}
