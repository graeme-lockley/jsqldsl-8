package za.co.no9.jsqldsl.tools;

import java.util.Optional;

public class FieldMetaData {
    final private String name;
    final private String fieldType;
    final private Optional<Integer> columnSize;
    final private Optional<Integer> subWidth;
    final private boolean isNullable;
    final private boolean isPrimaryKey;
    final private boolean isAutoIncrement;

    public FieldMetaData(String name, String fieldType, Optional<Integer> columnSize, Optional<Integer> subWidth, boolean isNullable, boolean isPrimaryKey, boolean isAutoIncrement) {
        this.name = name;
        this.fieldType = fieldType;
        this.columnSize = columnSize;
        this.subWidth = subWidth;
        this.isNullable = isNullable;
        this.isPrimaryKey = isPrimaryKey;
        this.isAutoIncrement = isAutoIncrement;
    }

    public String name() {
        return name;
    }

    public String fieldType() {
        return fieldType;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }

    @Override
    public String toString() {
        return "{name: " + name + ", fieldType: " + fieldType + ", columnSize: " + columnSize + ", subWidth: " + subWidth.orElse(null) + ", isNullable: " + isNullable + ", isPrimaryKey: " + isPrimaryKey + ", isAutoIncrement: " + isAutoIncrement + "}";
    }
}
