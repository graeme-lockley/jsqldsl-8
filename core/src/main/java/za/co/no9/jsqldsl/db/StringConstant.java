package za.co.no9.jsqldsl.db;

public class StringConstant extends StringOperations implements StringType {
    private final String value;

    private StringConstant(String value) {
        this.value = value;
    }

    public static StringConstant from(String value) {
        return new StringConstant(value);
    }

    @Override
    public String asString(Precedence precedence) {
        return '\'' + markupSQLConstantString(value) + '\'';
    }

    private static String markupSQLConstantString(String value) {
        StringBuilder sb = new StringBuilder();

        int stringLength = value.length();
        for (int i = 0; i < stringLength; i += 1) {
            char ch = value.charAt(i);
            if (ch == '\'') {
                sb.append('\'');
            }
            sb.append(ch);
        }
        return sb.toString();
    }
}
