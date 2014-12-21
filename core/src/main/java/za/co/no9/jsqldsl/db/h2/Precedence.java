package za.co.no9.jsqldsl.db.h2;

import stuff.BooleanType;

import java.util.function.BiFunction;

public enum Precedence {
    QUERY_WHERE(0),
    EQ_OPERATOR(1);

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
