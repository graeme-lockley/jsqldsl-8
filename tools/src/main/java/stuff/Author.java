package stuff;

public class Author implements TableRef {
    public final IntegerColumnReference AUTHOR_ID;
    public final IntegerColumnReference ID_NUMBER;
    public final StringColumnReference NAME;
    private final String reference;

    private Author(String reference) {
        this.reference = reference;

        AUTHOR_ID = new IntegerColumnReference(reference, "AUTHOR_ID");
        ID_NUMBER = new IntegerColumnReference(reference, "ID_NUMBER");
        NAME = new StringColumnReference(reference, "NAME");
    }

    public static Author as(String a) {
        return new Author(a);
    }

    public static Author ref() {
        return new Author("AUTHOR");
    }

    @Override
    public String asString() {
        return "AUTHOR as " + reference;
    }
}
