package stuff;

class IntegerColumnReference extends IntegerOperation implements OrderByExpression {
    private String tableName;
    private String columnName;

    IntegerColumnReference(String tableName, String columnName) {
        this.tableName = tableName;
        this.columnName = columnName;
    }

    public String toString() {
        return tableName + "." + columnName;
    }

    @Override
    public String asString(int precedence) {
        return tableName + "." + columnName;
    }
}
