package za.co.no9.jsqldsl.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ForeignKey {
    private final Optional<String> pkName;
    private final TableName pkTableName;
    private final List<String> pkColumnNames = new ArrayList<>();
    private final Optional<String> fkName;
    private final TableName fkTableName;
    private final List<String> fkColumnNames = new ArrayList<>();

    public ForeignKey(Optional<String> pkName, TableName pkTableName, String pkColumnName, Optional<String> fkName, TableName fkTableName, String fkColumnName) {
        this.pkName = pkName;
        this.pkTableName = pkTableName;
        this.pkColumnNames.add(pkColumnName);
        this.fkName = fkName;
        this.fkTableName = fkTableName;
        this.fkColumnNames.add(fkColumnName);
    }

    private ForeignKey(Optional<String> pkName, TableName pkTableName, Collection<String> pkColumnNames, Optional<String> fkName, TableName fkTableName, Collection<String> fkColumnNames) {
        this.pkName = pkName;
        this.pkTableName = pkTableName;
        this.pkColumnNames.addAll(pkColumnNames);
        this.fkName = fkName;
        this.fkTableName = fkTableName;
        this.fkColumnNames.addAll(fkColumnNames);
    }

    public Optional<String> pkName() {
        return pkName;
    }

    public Optional<String> fkName() {
        return fkName;
    }

    public TableName fkTableName() {
        return fkTableName;
    }

    public TableName pkTableName() {
        return pkTableName;
    }

    public ForeignKey addField(String pkColumnName, String fkColumnName) {
        List<String> newPkColumnNames = new ArrayList<>(pkColumnNames);
        newPkColumnNames.add(pkColumnName);
        List<String> newFkColumnNames = new ArrayList<>(fkColumnNames);
        newFkColumnNames.add(fkColumnName);
        return new ForeignKey(pkName, pkTableName, newPkColumnNames, fkName, fkTableName, newFkColumnNames);
    }
}
