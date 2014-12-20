package stuff;

public class Book implements TableRef {
    public final IntegerColumnReference BOOK_ID;
    public final StringColumnReference TITLE;
    public final IntegerColumnReference AUTHOR_ID;

    private final String reference;

    public Book(String reference) {
        this.reference = reference;

        BOOK_ID = new IntegerColumnReference(reference, "BOOK_ID");
        TITLE = new StringColumnReference(reference, "TITLE");
        AUTHOR_ID = new IntegerColumnReference(reference, "AUTHOR_ID");
    }

    public static Book as(String reference) {
        return new Book(reference);
    }

    public static Book ref() {
        return new Book("BOOK");
    }

    @Override
    public String asString() {
        return "BOOK as " + reference;
    }
}
