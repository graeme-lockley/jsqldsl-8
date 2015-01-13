package za.co.no9.jsqldsl.port.jsqldslmojo;

import za.co.no9.jsqldsl.port.jsqldslmojo.configuration.IncludeType;
import za.co.no9.jsqldsl.tools.TableMetaData;

import java.util.List;

public class TableFilter {
    private List<IncludeType> includes;

    public TableFilter(List<IncludeType> includes) {
        this.includes = includes;
    }

    public boolean filter(TableMetaData table) {
        for (IncludeType include : includes) {
            if (filter(table, include)) {
                return true;
            }
        }
        return false;
    }

    private boolean filter(TableMetaData table, IncludeType include) {
        return filterSchema(table, include.getSchema()) && filterTableName(table, include.getTable());
    }

    private boolean filterTableName(TableMetaData table, String tableName) {
        return tableName == null || tableName.equals(table.tableName().dbName());
    }

    private boolean filterSchema(TableMetaData table, String schema) {
        return schema == null || schema.equals(table.tableName().schema().orElse(""));
    }
}
