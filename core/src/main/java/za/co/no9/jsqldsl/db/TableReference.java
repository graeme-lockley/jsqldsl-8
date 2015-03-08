package za.co.no9.jsqldsl.db;

import java.util.Optional;

public abstract class TableReference {
    private Optional<String> alias;

    protected TableReference(Optional<String> alias) {
        this.alias = alias;
    }

    public String asString() {
        if (alias.isPresent()) {
            return name() + " AS " + alias.get();
        } else {
            return name();
        }
    }

    public String reference() {
        return alias.orElse(name());
    }

    public abstract String name();
}
