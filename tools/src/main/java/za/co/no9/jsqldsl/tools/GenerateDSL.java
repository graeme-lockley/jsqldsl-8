package za.co.no9.jsqldsl.tools;

import freemarker.template.TemplateException;
import za.co.no9.util.FreeMarkerUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GenerateDSL {
    public void generateTable(String packageName, TableMetaData tableMetaData) throws IOException, TemplateException {
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("packageName", packageName);
        dataModel.put("tableMetaData", tableMetaData);

        System.out.println(FreeMarkerUtils.template(dataModel, "za.co.no9.jsqldsl.tools.table.ftl"));
    }
}
