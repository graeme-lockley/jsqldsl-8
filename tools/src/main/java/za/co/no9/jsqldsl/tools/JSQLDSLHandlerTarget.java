package za.co.no9.jsqldsl.tools;

import za.co.no9.jsqldsl.drivers.DBDriver;
import za.co.no9.jsqldsl.port.jsqldslmojo.ConfigurationException;
import za.co.no9.jsqldsl.port.jsqldslmojo.Target;

import java.io.File;

public class JSQLDSLHandlerTarget {
    private final Target target;

    private JSQLDSLHandlerTarget(Target target) {
        this.target = target;
    }

    public static JSQLDSLHandlerTarget from(Target target) {
        return new JSQLDSLHandlerTarget(target);
    }

    public File generatorTargetRoot() {
        return new File(getConfigurationParentFile(), target.getDestination());
    }

    private File getConfigurationParentFile() {
        return target.getConfigurationParentFile();
    }

    public DBDriver getDBDriver() throws ConfigurationException {
        try {
            return (DBDriver) Class.forName(getDriverClassName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            throw new ConfigurationException("Unable to instantiate the DB handler " + getDriverClassName() + ".", ex);
        } catch (IllegalArgumentException ex) {
            throw new ConfigurationException("Unable to instantiate the DB handler as no driver property.", ex);
        }
    }

    private String getDriverClassName() {
        return target.getProperty("driver").orElseThrow(() -> new IllegalArgumentException("No property 'driver'."));
    }

    public String getTargetPackageName() {
        return target.getProperty("package").orElseThrow(() -> new IllegalArgumentException("No property 'package'."));
    }
}
