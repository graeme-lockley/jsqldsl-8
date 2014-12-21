package za.co.no9.jsqldsl.db.h2;

import java.util.Optional;

public abstract class TableRef {
    private Optional<String> alias;

    TableRef() {
        this.alias = Optional.empty();
    }

    TableRef(String alias) {
        this.alias = Optional.of(alias);
    }

    public String asString() {
        if (alias.isPresent()) {
            return name() + " AS " + alias.get();
        } else {
            return name();
        }
    }

    public abstract String name();
}
