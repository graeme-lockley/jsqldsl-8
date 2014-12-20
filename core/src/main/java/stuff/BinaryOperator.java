package stuff;

public interface BinaryOperator<X extends BaseType, Y extends BaseType> {

    String asString(int precedence);
}
