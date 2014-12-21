package za.co.no9.jsqldsl.db.h2;

import java.util.function.BiFunction;

public enum Precedence {
    LOWEST_PRECEDENCE(0),
    QUERY_WHERE(1),
    EQ_OPERATOR(2),
    HIGHEST_PRECEDENCE(1000);

    private int precedence;

    private Precedence(int precedence) {
        this.precedence = precedence;
    }

    public String asString(Precedence contextPrecedence, BaseType left, BaseType right, BiFunction<String, String, String> format) {
        if (precedence > contextPrecedence.precedence) {
            return format.apply(left.asString(this), right.asString(this));
        } else {
            return '(' + format.apply(left.asString(this), right.asString(this)) + ')';
        }
    }
}
