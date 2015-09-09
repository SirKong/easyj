package net.hs.easyj.maven.plugin.webx.mojo;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * 收集 UI 资源
 *
 * @author Gavin Hu
 * @create 2015/5/15
 */
@Mojo(name="collect-resources", defaultPhase= LifecyclePhase.PREPARE_PACKAGE, threadSafe = true)
public class CollectResourcesMojo extends AbstractWebxMojo {

    @Parameter(defaultValue = "${project.build.outputDirectory}")
    private File outputDirectory;

    @Override
    public void doExecute() throws Exception {
        try {
            //
            File javaDirectory = new File(baseDirectory, "/src/main/java");
            // collect java resources
            collectJavaResources(javaDirectory);
            //
        } catch (Exception e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
    }

    protected void collectJavaResources(File javaDirectory) throws IOException {
        //
        for(File sourceFile : javaDirectory.listFiles()) {
            if(sourceFile.isDirectory()) {
                collectJavaResources(sourceFile);
            } else {
                //
                String javaSource = FileUtils.fileRead(sourceFile);
                if(javaSource.contains("net.hs.easyj.web.component.annotation.UIComponent")) {
                    //
                    String componentName = sourceFile.getName().substring(0, sourceFile.getName().indexOf("."));
                    File targetFile = new File(outputDirectory, "/META-INF/web-components/" + componentName + "/" + sourceFile.getName());
                    //
                    FileUtils.copyFile(sourceFile, targetFile);
                }
            }
        }
    }

}
