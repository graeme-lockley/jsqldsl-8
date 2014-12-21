package ${packageName};

import za.co.no9.jsqldsl.db.h2.TableReference;

public class ${tableMetaData.tableName().dbName()} extends TableReference {
<#list tableMetaData.fields() as field>
public final ${field.fieldType()}ColumnReference ${field.name()};
</#list>

private ${tableMetaData.tableName().dbName()}(Optional
<String> alias) {
    super(alias);

<#list tableMetaData.fields() as field>
${field.name()} = ${field.fieldType()}ColumnReference.from(this, "${field.name()}");
</#list>
    }

    public static ${tableMetaData.tableName().dbName()} ref() {
    return new ${tableMetaData.tableName().dbName()}(Optional.
    <String>empty());
        }

        public static ${tableMetaData.tableName().dbName()} as(String alias) {
        return new ${tableMetaData.tableName().dbName()}(Optional.of(alias));
        }

        @Override
        public String name() {
        return "${tableMetaData.tableName().dbName()}";
        }
        }
