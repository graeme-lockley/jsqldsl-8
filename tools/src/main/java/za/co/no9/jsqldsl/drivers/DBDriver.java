package za.co.no9.jsqldsl.drivers;

import freemarker.template.TemplateException;
import za.co.no9.jsqldsl.tools.TableMetaData;

import java.io.File;
import java.io.IOException;

public interface DBDriver {
    void createDSLTable(File generatorTargetRoot, String packageName, TableMetaData tableMetaData) throws IOException, TemplateException;
}
