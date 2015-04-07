package za.co.no9.jsqldsl.tools.schemadoc;

import za.co.no9.jsqldsl.port.jsqldslmojo.Target;
import za.co.no9.jsqldsl.tools.HandlerTargetParent;

import java.util.Optional;

public class HandlerTarget extends HandlerTargetParent {
    protected HandlerTarget(Target target) {
        super(target);
    }

    public static HandlerTarget from(Target target) {
        return new HandlerTarget(target);
    }

    public Optional<String> template() {
        return target.getProperty("template");
    }
}
