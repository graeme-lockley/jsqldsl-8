package za.co.no9.jsqldsl.tools.schemadoc;

import freemarker.template.TemplateException;
import org.apache.maven.plugin.logging.Log;
import za.co.no9.jsqldsl.drivers.DBDriver;
import za.co.no9.jsqldsl.port.jsqldslmojo.ConfigurationException;
import za.co.no9.jsqldsl.port.jsqldslmojo.TableFilter;
import za.co.no9.jsqldsl.port.jsqldslmojo.Target;
import za.co.no9.jsqldsl.tools.DatabaseMetaData;
import za.co.no9.jsqldsl.tools.GenerationException;
import za.co.no9.jsqldsl.tools.TableMetaData;
import za.co.no9.jsqldsl.tools.ToolHandler;
import za.co.no9.util.FreeMarkerUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Handler implements ToolHandler {
    private Log log;
    private HandlerTarget target;

    @Override
    public void setup(Log log, Target target) {
        this.log = log;
        this.target = HandlerTarget.from(target);
    }

    @Override
    public void process(Connection connection, TableFilter tableFilter) throws SQLException, ConfigurationException, GenerationException {
        DBDriver dbDriver = target.getDBDriver();
        DatabaseMetaData databaseMetaData = dbDriver.databaseMetaData(connection);

        target.generatorTargetRoot().mkdirs();
        File outputFile = new File(target.generatorTargetRoot(), "output.dot");

        try (PrintStream fos = new PrintStream(new FileOutputStream(outputFile))) {
            fos.println(FreeMarkerUtils.stringTemplate(assembleMap(from("target", target)), "start", target.startDocumentTemplate()));
            for (TableMetaData table : databaseMetaData.allTables()) {
                if (tableFilter.filter(table)) {
                    log.info("SchemaDoc: " + table.tableName());
                    fos.println(FreeMarkerUtils.stringTemplate(assembleMap(from("target", target), from("tableMetaData", table)), "table", target.tableTemplate()));
                }
            }
            fos.println(FreeMarkerUtils.stringTemplate(assembleMap(from("target", target)), "end", target.endDocumentTemplate()));
        } catch (TemplateException | FileNotFoundException ex) {
            throw new GenerationException(ex);
        }

        try {
            String[] args = {"/bin/sh", "-c", "dot -T png -o " + (new File(target.generatorTargetRoot(), "output.png")).getAbsolutePath() + " " + outputFile.getAbsolutePath()};
            log.info("SchemaDoc: " + args[0] + " " + args[1] + " '" + args[2] + "'");
            Runtime.getRuntime().exec(args);
        } catch (IOException ex) {
            throw new GenerationException(ex);
        }
    }

    private Map<String, Object> assembleMap(Map.Entry<String, Object>... entries) {
        Map<String, Object> result = new HashMap<>();
        Stream.of(entries).forEach(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }

    private static MapEntry from(String key, Object value) {
        return new MapEntry(key, value);
    }


    static class MapEntry implements Map.Entry<String, Object> {
        private String key;
        private Object value;

        MapEntry(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String getKey() {
            return key;
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public Object setValue(Object value) {
            Object oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }
}