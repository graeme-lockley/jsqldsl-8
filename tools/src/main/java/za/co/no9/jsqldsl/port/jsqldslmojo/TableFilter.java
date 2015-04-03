package za.co.no9.jsqldsl.port.jsqldslmojo;

import za.co.no9.jsqldsl.port.jsqldslmojo.configuration.TablePatternType;
import za.co.no9.jsqldsl.tools.TableMetaData;

import java.util.List;

public class TableFilter {
    private List<TablePatternType> includes;

    public TableFilter(List<TablePatternType> includes) {
        this.includes = includes;
    }

    public boolean filter(TableMetaData table) {
        for (TablePatternType include : includes) {
            if (filter(table, include)) {
                return true;
            }
        }
        return false;
    }

    private boolean filter(TableMetaData table, TablePatternType include) {
        return filterSchema(table, include.getSchema()) && filterTableName(table, include.getTable());
    }

    private boolean filterTableName(TableMetaData table, String tableName) {
        return tableName == null || tableName.equals(table.tableName().dbName());
    }

    private boolean filterSchema(TableMetaData table, String schema) {
        return schema == null || schema.equals(table.tableName().schema().orElse(""));
    }
}
