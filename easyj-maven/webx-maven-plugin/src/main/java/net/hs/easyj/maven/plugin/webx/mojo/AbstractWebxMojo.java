package net.hs.easyj.maven.plugin.webx.mojo;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

/**
 * 抽象的 Web 扩展插件
 *
 *
 * Created by gavin on 15/5/18.
 */
public abstract class AbstractWebxMojo extends AbstractMojo {

    @Parameter(defaultValue = "${session}", readonly = true, required = true )
    protected MavenSession session;
    @Parameter(defaultValue = "${project.basedir}")
    protected File baseDirectory;
    @Parameter(defaultValue = "${project.build.sourceEncoding}")
    protected String sourceEncoding;
    @Parameter(defaultValue = "${project.build.outputDirectory}")
    protected File outputDirectory;
    @Parameter(defaultValue = "${project.basedir}/src/main/java")
    protected File javaDirectory;
    @Parameter(defaultValue = "${project.basedir}/src/main/resources")
    protected File resourcesDirectory;
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        //
        try {
            File webComponentsXml = new File(resourcesDirectory, "META-INF/web-components.xml");
            if(!webComponentsXml.exists()) {
                throw new UnsupportedOperationException("The current project is not webx project!!!");
            }
            //
            doExecute();
            //
        } catch (Exception e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
    }

    protected abstract void doExecute() throws Exception;

}
