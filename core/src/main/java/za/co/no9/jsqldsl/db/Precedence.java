package za.co.no9.jsqldsl.db;

import java.util.function.BiFunction;
import java.util.function.Function;

public enum Precedence {
    LOWEST_PRECEDENCE(0),
    QUERY_WHERE(1),

    OR_OPERATOR(2),
    AND_OPERATOR(3),

    NOT_OPERATOR(0),

    IS_NULL(0),
    IS_NOT_NULL(0),

    GE_OPERATOR(5),
    GT_OPERATOR(5),
    LE_OPERATOR(5),
    LT_OPERATOR(5),

    EQ_OPERATOR(6),
    NOTEQ_OPERATOR(6),

    HIGHEST_PRECEDENCE(1000);

    private int ordinal;

    private Precedence(int ordinal) {
        this.ordinal = ordinal;
    }

    public String asString(Precedence contextPrecedence, BaseType left, BaseType right, BiFunction<String, String, String> format) {
        if (ordinal >= contextPrecedence.ordinal) {
            return format.apply(left.asString(this), right.asString(this));
        } else {
            return '(' + format.apply(left.asString(this), right.asString(this)) + ')';
        }
    }

    public String asString(BaseType value, Function<String, String> format) {
        return format.apply(value.asString(this));
    }
}
