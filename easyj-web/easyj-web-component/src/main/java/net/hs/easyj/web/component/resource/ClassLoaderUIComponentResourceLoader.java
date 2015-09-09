package net.hs.easyj.web.component.resource;

import net.hs.easyj.web.component.exception.UIComponentResourceNotFoundException;
import org.springframework.util.ClassUtils;

import java.net.URL;

/**
 * 基于类加载器的 UI 组件资源加载器
 *
 * @author Gavin Hu
 * @create 2015/5/15
 */
public class ClassLoaderUIComponentResourceLoader extends AbstractUIComponentResourceLoader {

    private static final String CLASSPATH = "META-INF/web-components/";

    private ClassLoader classLoader;

    public ClassLoaderUIComponentResourceLoader() {
        this(ClassUtils.getDefaultClassLoader());
    }

    public ClassLoaderUIComponentResourceLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    protected UIComponentResource doLoad(String name) {
        URL url = classLoader.getResource(CLASSPATH + name);
        if(url==null) {
            throw new UIComponentResourceNotFoundException(name);
        }
        return new ClassLoaderUIComponentResource(name, CLASSPATH + name, classLoader);
    }

}
