package za.co.no9.jsqldsl.tools;

import java.util.*;

public class TableMetaData {
    private final TableName tableName;
    private final FieldMetaData[] fieldsMetaData;
    private final Collection<ForeignKey> constraints;

    public TableMetaData(TableName tableName, FieldMetaData[] fieldsMetaData, Collection<ForeignKey> constraints) {
        this.tableName = tableName;
        this.fieldsMetaData = fieldsMetaData;
        this.constraints = new ArrayList<>(constraints);
    }

    public TableName tableName() {
        return tableName;
    }

    public Set<String> primaryKeyFieldNames() {
        Set<String> result = new HashSet<>();

        for (FieldMetaData fieldMetaData : fieldsMetaData) {
            if (fieldMetaData.isPrimaryKey()) {
                result.add(fieldMetaData.name());
            }
        }

        return result;
    }

    public Set<String> autoIncrementFieldNames() {
        Set<String> result = new HashSet<>();

        for (FieldMetaData fieldMetaData : fieldsMetaData) {
            if (fieldMetaData.isAutoIncrement()) {
                result.add(fieldMetaData.name());
            }
        }

        return result;
    }

    public List<FieldMetaData> fields() {
        return Arrays.asList(fieldsMetaData);
    }

    public ForeignKey[] foreignKeys() {
        return constraints.toArray(new ForeignKey[constraints.size()]);
    }
}
