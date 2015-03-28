package za.co.no9.jsqldsl.tools.jsqldsl;

import za.co.no9.jsqldsl.drivers.DBDriver;
import za.co.no9.jsqldsl.port.jsqldslmojo.ConfigurationException;
import za.co.no9.jsqldsl.port.jsqldslmojo.Target;

import java.io.File;

public class HandlerTarget {
    private final Target target;

    private HandlerTarget(Target target) {
        this.target = target;
    }

    public static HandlerTarget from(Target target) {
        return new HandlerTarget(target);
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
