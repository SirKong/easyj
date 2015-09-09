package net.hs.easyj.web.component.scanner;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 基于类加载器的 UI 组件扫描器
 *
 * @author Gavin Hu
 * @create 2015/5/16
 */
public class ClassLoaderUIComponentScanner extends AbstractUIComponentScanner implements BeanClassLoaderAware {

    private static final Pattern UI_COMPONENT_PATTERN = Pattern.compile("ui:component=\"(.*?)\"");

    private String prefix = "META-INF/web-components/";

    private String suffix = ".html";

    private ClassLoader classLoader;

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    protected Set<String> doScan(String viewName) throws Exception {
        //
        Set<String> componentNames = new HashSet<>();
        String viewLocation = getViewLocation(viewName);
        try(InputStream in = classLoader.getResourceAsStream(viewLocation)) {
            if(in!=null) {
                String html = FileCopyUtils.copyToString(new InputStreamReader(in));
                //
                Matcher matcher = UI_COMPONENT_PATTERN.matcher(html);
                while (matcher.find()) {
                    componentNames.add(matcher.group(1));
                }
            }
        }
        return componentNames;
    }

    private String getViewLocation(String viewName) {
        return this.prefix + viewName + this.suffix;
    }


}
