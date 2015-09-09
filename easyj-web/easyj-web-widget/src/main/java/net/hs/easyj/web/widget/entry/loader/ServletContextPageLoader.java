package net.hs.easyj.web.widget.entry.loader;

import net.hs.easyj.web.widget.entry.config.PageConfig;
import net.hs.easyj.web.widget.entry.config.WidgetConfig;
import net.hs.easyj.web.widget.entry.config.PositionConfig;

import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.Map;

/**
 * 基于 Servlet 上下文的页面配置加载器
 *
 * @author Gavin Hu
 * @create 2015/2/26
 */
public class ServletContextPageLoader extends AbstractPageLoader {

    private ServletContext servletContext;

    public ServletContextPageLoader(ServletContext servletContext) {
        this.servletContext = servletContext;
        super.setCache("true".equals(servletContext.getInitParameter("cachePage")));
        super.setPrefix("/WEB-INF/pages/");
        super.setSuffix(".xml");
    }

    @Override
    public PageConfig doLoad(String path, Map<String, String> params) {

        try {
            String pageLocation = getPageLocation(path);
            InputStream pageSource = servletContext.getResourceAsStream(pageLocation);
            if(pageSource==null) {
                throw new IllegalArgumentException(String.format("页面定义文件 %s 不存在！", pageLocation));
            }
            //
            JAXBContext context = JAXBContext.newInstance(PageConfig.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            PageConfig pageConfig = (PageConfig) unmarshaller.unmarshal(pageSource);
            pageConfig.setPath(path);
            for(PositionConfig positionConfig : pageConfig.getPositionConfigs()) {
                positionConfig.setPageConfig(pageConfig);
                for(WidgetConfig widgetConfig : positionConfig.getWidgetConfigs()) {
                    widgetConfig.setPositionConfig(positionConfig);
                }
            }
            //
            if (pageConfig.getParent()!=null) {
                PageConfig parentPageConfig = load(pageConfig.getParent(), params);
                mergePageConfigFromParent(pageConfig, parentPageConfig);
            }
            //
            return pageConfig;
        } catch (Exception e) {
            throw new PageLoaderException("页面配置文件加载异常！", e);
        }
    }

}
