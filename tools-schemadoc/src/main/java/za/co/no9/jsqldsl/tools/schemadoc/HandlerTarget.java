package za.co.no9.jsqldsl.tools.schemadoc;

import za.co.no9.jsqldsl.port.jsqldslmojo.Target;
import za.co.no9.jsqldsl.tools.HandlerTargetParent;

import java.io.File;
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

    public File getTemplateOutputFile() {
        Optional<String> output = target.getProperty("template-output");
        return output.isPresent() ? new File(output.get()) : new File(generatorTargetRoot(), "output.dot");
    }

    public String getPostCommand() {
        return target.getProperty("post-command").orElse("dot -T png -o " + (new File(generatorTargetRoot(), "output.png")).getAbsolutePath() + " " + getTemplateOutputFile().getAbsolutePath());
    }
}
