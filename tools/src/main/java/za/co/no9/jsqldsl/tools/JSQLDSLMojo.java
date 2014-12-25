package za.co.no9.jsqldsl.tools;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.util.Arrays;

@Mojo(name = "jsqldsl")
public class JSQLDSLMojo extends AbstractMojo {
    @Parameter(defaultValue = "${project.build.testSourceDirectory}", readonly = true)
    private File outputDirectory;

    @Parameter(defaultValue = "jsqldsl", property = "goalPrefix", required = true)
    private String goalPrefix;

    @Parameter(defaultValue = "${id}", readonly = true)
    private String bob;

    @Parameter(alias="includes")
    private String[] mIncludes;

    @Parameter(alias="includes")
    private String[] mExcludes;

    public void setExcludes( String[] excludes ) {
        getLog().info("setExcludes: " + Arrays.toString(excludes));
        mExcludes = excludes; }

    public void setIncludes( String[] includes ) {
        getLog().info("setIncludes: " + Arrays.toString(includes));
        mIncludes = includes; }

    @Override
    public void execute() throws MojoExecutionException {
        getLog().info("JSQLDSLMojo: Start");
        getLog().info("JSQLDSLMojo: goalPrefix: [" + goalPrefix + "]  outputDirectory: [" + outputDirectory + "]    bob: [" + bob + "]");
        for (Object entry : getPluginContext().entrySet()) {
            getLog().info(entry.toString());
        }
        getLog().info("Excludes: " + Arrays.toString(mExcludes));
        getLog().info("Includes: " + Arrays.toString(mIncludes));
        getLog().info("JSQLDSLMojo: End");
    }
}
