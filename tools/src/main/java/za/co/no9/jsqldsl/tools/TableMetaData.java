package za.co.no9.jsqldsl.tools;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TableMetaData {
    private final TableName tableName;
    private final FieldMetaData[] fieldsMetaData;

    public TableMetaData(TableName tableName, FieldMetaData[] fieldsMetaData) {
        this.tableName = tableName;
        this.fieldsMetaData = fieldsMetaData;
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
}
