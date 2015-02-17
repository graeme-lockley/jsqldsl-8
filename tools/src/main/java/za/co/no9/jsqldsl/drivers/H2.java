package za.co.no9.jsqldsl.drivers;

import za.co.no9.jsqldsl.tools.GenerateDSL;
import za.co.no9.jsqldsl.tools.GenerationException;
import za.co.no9.jsqldsl.tools.TableMetaData;

import java.io.File;

public class H2 implements DBDriver {
    @Override
    public void createDSLTable(File generatorTargetRoot, String packageName, TableMetaData tableMetaData) throws GenerationException {
        new GenerateDSL().generateTable(generatorTargetRoot, packageName, tableMetaData);
    }
}
