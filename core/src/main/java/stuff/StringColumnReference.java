package stuff;

public class StringColumnReference extends StringOperation implements OrderByExpression {
    private String tableName;
    private String columnName;

    public StringColumnReference(String tableName, String columnName) {
        this.tableName = tableName;
        this.columnName = columnName;
    }

    public String toString() {
        return tableName + "." + columnName;
    }

    public OrderByExpression desc() {
        return new DescSortOrder(this);
    }

    @Override
    public String asString(int precedence) {
        return tableName + "." + columnName;
    }
}
