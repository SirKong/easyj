package net.hs.easyj.web.component.loader;

import net.hs.easyj.web.component.UIComponent;
import net.hs.easyj.web.component.factory.UIComponentFactory;
import net.hs.easyj.web.component.support.aware.HttpServletRequestAware;
import net.hs.easyj.web.component.support.aware.ServletContextAware;
import org.springframework.util.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * UI 组件 AJAX 数据加载器
 *
 * @author Gavin Hu
 * @create 2015/5/22
 */
public class UIComponentAjaxDataLoader implements UIComponentDataLoader {

    private static final String HEADER_UI_COMPONENT = "UI-Component";

    private static final String HEADER_X_REQUESTED_WITH = "X-Requested-With";

    private UIComponentFactory uiComponentFactory;

    public void setUiComponentFactory(UIComponentFactory uiComponentFactory) {
        this.uiComponentFactory = uiComponentFactory;
    }

    @Override
    public boolean support(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader(HEADER_X_REQUESTED_WITH))
                && StringUtils.hasText(request.getHeader(HEADER_UI_COMPONENT));
    }

    @Override
    public Object loadData(HttpServletRequest request) {
        //
        String componentName = request.getHeader(HEADER_UI_COMPONENT);
        UIComponent uiComponent = uiComponentFactory.get(componentName);
        // inject through aware interfaces
        if(uiComponent instanceof HttpServletRequestAware) {
            ((HttpServletRequestAware) uiComponent).setHttpServletRequest(request);
        }
        if(uiComponent instanceof ServletContextAware) {
            ServletContext servletContext = request.getSession().getServletContext();
            ((ServletContextAware) uiComponent).setServletContext(servletContext);
        }
        return uiComponent.getData();
    }

}
