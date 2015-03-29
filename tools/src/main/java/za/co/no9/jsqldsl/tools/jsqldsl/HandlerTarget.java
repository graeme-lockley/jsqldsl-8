package za.co.no9.jsqldsl.tools.jsqldsl;

import za.co.no9.jsqldsl.port.jsqldslmojo.Target;
import za.co.no9.jsqldsl.tools.HandlerTargetParent;

public class HandlerTarget extends HandlerTargetParent {
    protected HandlerTarget(Target target) {
        super(target);
    }

    public static HandlerTarget from(Target target) {
        return new HandlerTarget(target);
    }

    public String getTargetPackageName() {
        return target.getProperty("package").orElseThrow(() -> new IllegalArgumentException("No property 'package'."));
    }
}
