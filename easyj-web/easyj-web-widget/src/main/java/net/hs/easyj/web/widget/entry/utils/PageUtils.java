package net.hs.easyj.web.widget.entry.utils;

import net.hs.easyj.web.widget.entry.config.PageConfig;
import net.hs.easyj.web.widget.entry.config.WidgetConfig;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Gavin Hu on 2015/2/26.
 */
public class PageUtils {

    public static void setPageConfig(HttpServletRequest request, PageConfig pageConfig) {
        request.getSession().setAttribute("pageConfig", pageConfig);
        request.getSession().setAttribute(PageConfig.class.getName(), pageConfig);
    }

    public static PageConfig getPageConfig(HttpServletRequest request) {
        return (PageConfig) request.getSession().getAttribute(PageConfig.class.getName());
    }

    public static void setWidgetConfig(HttpServletRequest request, WidgetConfig widgetConfig) {
        request.setAttribute("widgetConfig", widgetConfig);
        request.setAttribute(WidgetConfig.class.getName(), widgetConfig);
    }

    public static WidgetConfig getWidgetConfig(HttpServletRequest request) {
        return (WidgetConfig) request.getAttribute(WidgetConfig.class.getName());
    }

}
