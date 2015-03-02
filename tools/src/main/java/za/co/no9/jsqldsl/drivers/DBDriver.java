package za.co.no9.jsqldsl.drivers;

import za.co.no9.jsqldsl.tools.DatabaseMetaData;
import za.co.no9.jsqldsl.tools.GenerationException;
import za.co.no9.jsqldsl.tools.TableMetaData;

import java.io.File;
import java.sql.Connection;

public interface DBDriver {
    DatabaseMetaData databaseMetaData(Connection connection);
    void createDSLTable(File generatorTargetRoot, String packageName, TableMetaData tableMetaData) throws GenerationException;
}
