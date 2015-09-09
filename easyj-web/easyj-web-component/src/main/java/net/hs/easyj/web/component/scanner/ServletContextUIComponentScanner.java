package net.hs.easyj.web.component.scanner;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * UI 组件扫描器
 *
 * @author Gavin Hu
 * @create 2015/5/6
 */
public class ServletContextUIComponentScanner extends AbstractUIComponentScanner implements ServletContextAware {

    private static final Pattern UI_COMPONENT_PATTERN = Pattern.compile("ui:component=\"(.*?)\"");

    private String prefix = "/WEB-INF/components/";

    private String suffix = ".html";

    private ServletContext servletContext;

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public Set<String> doScan(String viewName) throws Exception {
        //
        Set<String> componentNames = new HashSet<>();
        String viewLocation = getViewLocation(viewName);
        try(InputStream in = servletContext.getResourceAsStream(viewLocation)) {
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
