package net.hs.easyj.maven.plugin.webx.mojo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 新建 UI 组件
 *
 * @author Gavin Hu
 * @create 2015/5/16
 */
@Mojo(name="new-component", threadSafe = true)
public class NewComponentMojo extends AbstractWebxMojo {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/M/d");

    public static final String[] COMPONENT_RESOURCE_SUFFIXES = new String[]{
            ".java", ".html", ".js", "Validate.js", "Adapter.js", "Show.html", "XDoc.xml"};

    @Override
    public void doExecute() throws Exception {
        //
        try {
            Map<String, String> arguments= resolveArguments();
            //
            newComponent(arguments);
            //
        } catch (Exception e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
    }

    private Map<String, String> resolveArguments() {
        Map<String, String> arguments = new HashMap<>();
        //
        String className = System.getProperty("name");
        if (StringUtils.isEmpty(className)) {
            throw new IllegalArgumentException("Argument name can not be empty!");
        }
        String title = System.getProperty("title", className.substring(className.lastIndexOf(".") + 1));
        String category = System.getProperty("category", "默认分类");
        String extendsClassName = System.getProperty("extends", null);
        //
        arguments.put("className", className);
        arguments.put("classSimpleName", className.substring(className.lastIndexOf(".") + 1));
        arguments.put("packageName", className.substring(0, className.lastIndexOf(".")));
        arguments.put("componentName", className.substring(className.lastIndexOf(".") + 1));
        arguments.put("title", title);
        arguments.put("category", category);
        arguments.put("author", System.getProperty("user.name"));
        arguments.put("create", dateFormat.format(new Date()));
        //
        if(extendsClassName!=null) {
            arguments.put("extendsClassName", extendsClassName);
            arguments.put("extendsPackageName", extendsClassName.substring(0, extendsClassName.lastIndexOf(".")));
            arguments.put("extendsComponentName", extendsClassName.substring(extendsClassName.lastIndexOf(".") + 1));
        } else {
            arguments.put("extendsClassName", "net.hs.easyj.web.component.support.UIComponentSupport");
            arguments.put("extendsComponentName", "UIComponentSupport");
        }
        //
        return arguments;
    }

    protected void newComponent(Map<String, String> arguments) throws IOException, TemplateException {
        //
        String packageName = arguments.get("packageName");
        String componentName = arguments.get("componentName");
        String extendsComponentName = arguments.get("extendsComponentName");
        //
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "META-INF/web-component");
        //
        for(String suffix : COMPONENT_RESOURCE_SUFFIXES) {
            //
            File targetFile = null;
            String templateName = "UIComponent" + suffix;
            if(templateName.endsWith("UIComponent.java")) {
                targetFile = new File(javaDirectory, StringUtils.replace(packageName, ".", "/") + "/" + componentName + ".java");
            } else if(templateName.endsWith("UIComponent.html")) {
                targetFile = new File(resourcesDirectory, "META-INF/web-components/" + componentName + "/" + componentName + ".html");
            } else if(templateName.endsWith("UIComponent.js")) {
                targetFile = new File(resourcesDirectory, "META-INF/web-components/" + componentName + "/" + componentName + ".js");
            } else if(templateName.endsWith("UIComponentValidate.js")) {
                targetFile = new File(resourcesDirectory, "META-INF/web-components/" + componentName + "/" + componentName + "Validate.js");
            } else if(templateName.endsWith("UIComponentAdapter.js")) {
                targetFile = new File(resourcesDirectory, "META-INF/web-components/" + componentName + "/" + componentName + "Adapter.js");
            } else if(templateName.endsWith("UIComponentShow.html")) {
                targetFile = new File(resourcesDirectory, "META-INF/web-components/" + componentName + "/" + componentName + "Show.html");
            } else if(templateName.endsWith("UIComponentXDoc.xml")) {
                targetFile = new File(resourcesDirectory, "META-INF/web-components/" + componentName + "/" + componentName + "XDoc.xml");
            } else {
                throw new IllegalArgumentException("Unsupported templateName " + templateName);
            }
            //
            targetFile.getParentFile().mkdirs();
            targetFile.createNewFile();
            //
            Template uiTemplate = cfg.getTemplate(templateName);
            uiTemplate.process(arguments, new FileWriter(targetFile));
            //
            if(!"UIComponentSupport".equals(extendsComponentName) && !suffix.equals(".java")) {
                File sourceFile = new File(resourcesDirectory, "META-INF/web-components/" + extendsComponentName + "/" + extendsComponentName + suffix);
                //
                String content = FileUtils.readFileToString(sourceFile, sourceEncoding);
                //
                String newContent = StringUtils.replace(content, extendsComponentName, componentName);
                //
                FileUtils.write(targetFile, newContent, sourceEncoding);
            }
            //
        }
    }
}
