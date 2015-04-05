package za.co.no9.jsqldsl.tools.schemadoc;

import za.co.no9.jsqldsl.port.jsqldslmojo.Target;
import za.co.no9.jsqldsl.tools.HandlerTargetParent;

public class HandlerTarget extends HandlerTargetParent {
    protected HandlerTarget(Target target) {
        super(target);
    }

    public static HandlerTarget from(Target target) {
        return new HandlerTarget(target);
    }

    public String startDocumentTemplate() {
        return target.getProperty("start-document-template").orElse("").trim();
    }

    public String tableTemplate() {
        return target.getProperty("table-template").orElse("").trim();
    }

    public String endDocumentTemplate() {
        return target.getProperty("end-document-template").orElse("").trim();
    }
}
