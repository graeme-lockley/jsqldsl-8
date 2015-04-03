package za.co.no9.jsqldsl.port.jsqldslmojo;

import za.co.no9.jsqldsl.port.jsqldslmojo.configuration.TablePatternType;
import za.co.no9.jsqldsl.tools.TableMetaData;

import java.util.List;

public class TableFilter {
    private final List<TablePatternType> includes;
    private final List<TablePatternType> excludes;

    public TableFilter(List<TablePatternType> includes, List<TablePatternType> excludes) {
        this.includes = includes;
        this.excludes = excludes;
    }

    public boolean filter(TableMetaData table) {
        return filterMatch(includes, table) && !filterMatch(excludes, table);
    }

    private boolean filterMatch(List<TablePatternType> patterns, TableMetaData table) {
        for (TablePatternType pattern : patterns) {
            if (filter(table, pattern)) {
                return true;
            }
        }
        return false;
    }

    private boolean filter(TableMetaData table, TablePatternType pattern) {
        return filterSchema(table, pattern.getSchema()) && filterTableName(table, pattern.getTable());
    }

    private boolean filterTableName(TableMetaData table, String tableName) {
        return tableName == null || tableName.equals(table.tableName().dbName());
    }

    private boolean filterSchema(TableMetaData table, String schema) {
        return schema == null || schema.equals(table.tableName().schema().orElse(""));
    }
}
