package stuff;

public class DescSortOrder implements OrderByExpression {
    private StringColumnReference stringColumnReference;

    public DescSortOrder(StringColumnReference stringColumnReference) {
        this.stringColumnReference = stringColumnReference;
    }

    public String toString() {
        return stringColumnReference.toString() + " DESC";
    }
}
