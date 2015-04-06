package za.co.no9.jsqldsl.tools.schemadoc;

import org.apache.commons.io.IOUtils;
import za.co.no9.jsqldsl.port.jsqldslmojo.Target;
import za.co.no9.jsqldsl.tools.HandlerTargetParent;

import java.io.IOException;
import java.io.InputStream;

public class HandlerTarget extends HandlerTargetParent {
    private static final String DEFAULT_RESOURCE = "";
    private static final String START_DOCUMENT_TEMPLATE_RESOURCE = "schemadoc/start-document-template.ftl";
    private static final String TABLE_TEMPLATE_RESOURCE = "schemadoc/table-template.ftl";
    private static final String END_DOCUMENT_TEMPLATE_RESOURCE = "schemadoc/end-document-template.ftl";

    protected HandlerTarget(Target target) {
        super(target);
    }

    public static HandlerTarget from(Target target) {
        return new HandlerTarget(target);
    }

    public String startDocumentTemplate() {
        return target.getProperty("start-document-template").orElse(loadResource(START_DOCUMENT_TEMPLATE_RESOURCE)).trim();
    }

    public String tableTemplate() {
        return target.getProperty("table-template").orElse(loadResource(TABLE_TEMPLATE_RESOURCE)).trim();
    }

    public String endDocumentTemplate() {
        return target.getProperty("end-document-template").orElse(loadResource(END_DOCUMENT_TEMPLATE_RESOURCE)).trim();
    }

    private String loadResource(String resourceName) {
        ClassLoader loader = getClass().getClassLoader();

        try (InputStream is = loader.getResourceAsStream(resourceName)) {
            return IOUtils.toString(is);
        } catch (IOException e) {
            return DEFAULT_RESOURCE;
        }
    }
}
