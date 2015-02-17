package za.co.no9.jsqldsl.drivers;

import za.co.no9.jsqldsl.tools.GenerationException;
import za.co.no9.jsqldsl.tools.TableMetaData;

import java.io.File;

public interface DBDriver {
    void createDSLTable(File generatorTargetRoot, String packageName, TableMetaData tableMetaData) throws GenerationException;
}
