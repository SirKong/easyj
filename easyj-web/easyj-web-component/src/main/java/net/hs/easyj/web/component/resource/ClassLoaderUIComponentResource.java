package net.hs.easyj.web.component.resource;

import java.io.InputStream;

/**
 * 基于类加载器的组件资源实现
 *
 * @author Gavin Hu
 * @create 2015/5/15
 */
public class ClassLoaderUIComponentResource implements UIComponentResource {

    private String name;

    private String classpath;

    private ClassLoader classLoader;

    public ClassLoaderUIComponentResource(String name, String classpath, ClassLoader classLoader) {
        this.name = name;
        this.classpath = classpath;
        this.classLoader = classLoader;
    }

    @Override
    public String getComponentName() {
        return name;
    }

    @Override
    public InputStream getComponentXDoc() {
        return classLoader.getResourceAsStream(classpath + "/" + name + "XDoc.xml");
    }

    @Override
    public InputStream getComponentClass() {
        return classLoader.getResourceAsStream(classpath + "/" + name + ".java");
    }

    @Override
    public InputStream getComponentUI() {
        return classLoader.getResourceAsStream(classpath + "/" + name + ".html");
    }

    @Override
    public InputStream getComponentShowUI() {
        return classLoader.getResourceAsStream(classpath + "/" + name + "Show.html");
    }

    @Override
    public InputStream getComponentScript() {
        return classLoader.getResourceAsStream(classpath+ "/" + name + ".js");
    }

    @Override
    public InputStream getComponentAdapterScript() {
        return classLoader.getResourceAsStream(classpath + "/" + name + "Adapter.js");
    }

	@Override
	public InputStream getComponentValidateScript() {
        return classLoader.getResourceAsStream(classpath + "/" + name + "Validate.js");

	}

}
