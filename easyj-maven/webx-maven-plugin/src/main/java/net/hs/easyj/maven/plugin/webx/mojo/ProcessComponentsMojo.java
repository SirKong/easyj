package net.hs.easyj.maven.plugin.webx.mojo;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Collection;

/**
 * 编译 UI 组件
 *
 * @author Gavin Hu
 * @create 2015/5/26
 */
@Mojo(name="process-components", threadSafe = true)
public class ProcessComponentsMojo extends AbstractWebxMojo {

    @Parameter(required = true)
    private String resourceId;

    @Override
    protected void doExecute() throws Exception {
        //
        collectJavaFiles(javaDirectory);
        //
        mergeJavaScripts();
    }

    protected void collectJavaFiles(File javaDirectory) throws IOException {
        //
        for(File sourceFile : javaDirectory.listFiles()) {
            if(sourceFile.isDirectory()) {
                collectJavaFiles(sourceFile);
            } else {
                //
                String javaSource = org.codehaus.plexus.util.FileUtils.fileRead(sourceFile);
                if(javaSource.contains("net.hs.easyj.web.component.annotation.UIComponent")) {
                    //
                    String componentName = sourceFile.getName().substring(0, sourceFile.getName().indexOf("."));
                    File targetFile = new File(outputDirectory, "/META-INF/web-components/" + componentName + "/" + sourceFile.getName());
                    //
                    org.codehaus.plexus.util.FileUtils.copyFile(sourceFile, targetFile);
                }
            }
        }
    }

    protected void mergeJavaScripts() throws IOException {
        File webComponentsDirectory = new File(resourcesDirectory, "/META-INF/web-components");
        //
        File[] webComponentDirectories = webComponentsDirectory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        //
        StringBuilder uiComponentsJsBuilder = new StringBuilder("$(document).ready(function(){\n");
        for(File webComponentDirectory : webComponentDirectories) {
            StringBuilder uiComponentJsBuilder = new StringBuilder("$(document).ready(function(){\n");
            Collection<File> jsFiles = FileUtils.listFiles(webComponentDirectory, new String[]{"js"}, false);
            for(File jsFile : jsFiles) {
                String jsSource = FileUtils.readFileToString(jsFile, sourceEncoding);
                uiComponentJsBuilder.append(jsSource).append("\n");
                uiComponentsJsBuilder.append(jsSource).append("\n");
            }
            uiComponentJsBuilder.append("});");
            //
            File uiComponentJsFile = new File(outputDirectory,
                    String.format("/META-INF/web-resources/%s/%s.js", resourceId, webComponentDirectory.getName()));
            FileUtils.writeStringToFile(uiComponentJsFile, uiComponentJsBuilder.toString(), sourceEncoding);
        }
        uiComponentsJsBuilder.append("});");
        //
        File uiComponentsJsFile = new File(outputDirectory,
                String.format("/META-INF/web-resources/%s/%s.js", resourceId, "AllInOne"));
        FileUtils.writeStringToFile(uiComponentsJsFile, uiComponentsJsBuilder.toString(), sourceEncoding);
    }

}
