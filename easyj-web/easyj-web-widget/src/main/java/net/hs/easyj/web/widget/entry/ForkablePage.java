package net.hs.easyj.web.widget.entry;

import net.hs.easyj.web.extender.fork.Forkable;
import net.hs.easyj.web.extender.fork.ForkedHttpServletRequest;
import net.hs.easyj.web.extender.fork.ForkedHttpServletResponse;
import net.hs.easyj.web.extender.fork.ForkedRequestAndResponse;
import net.hs.easyj.web.widget.entry.config.PageConfig;
import net.hs.easyj.web.widget.entry.config.WidgetConfig;
import net.hs.easyj.web.widget.entry.loader.PageLoader;
import net.hs.easyj.web.widget.entry.loader.ServletContextPageLoader;
import net.hs.easyj.web.widget.entry.utils.PageUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 可拆分的页面
 *
 * @author Gavin Hu
 * @create 2015/2/26
 */
public class ForkablePage implements Forkable {

    private static final String DEFAULT_PAGE_SUFFIX = ".html";

    private PageLoader pageLoader;

    @Override
    public void setServletContext(ServletContext context) {
        this.pageLoader = newPageLoader(context);
    }

    protected PageLoader newPageLoader(ServletContext context) {
        return new ServletContextPageLoader(context);
    }

    @Override
    public boolean canFork(HttpServletRequest req) {
        //
        String requestURI = req.getRequestURI();
        int semicolonIndex = requestURI.indexOf(";");
        if(semicolonIndex>0) {
            requestURI = requestURI.substring(0, semicolonIndex);
        }
        if(!requestURI.endsWith(DEFAULT_PAGE_SUFFIX)) {
            return false;
        }
        //
        String pagePath = getPagePath(req);
        Map<String, String> params = getRequestParams(req);
        //
        PageConfig pageConfig = pageLoader.load(pagePath, params);
        PageUtils.setPageConfig(req, pageConfig);
        //
        return pageConfig!=null;
    }

    private String getPagePath(HttpServletRequest req) {
        String pathInfo = req.getPathInfo();
        int indexDot = pathInfo.indexOf(".");
        if(indexDot>0) {
            return pathInfo.substring(0, indexDot);
        }
        return pathInfo;
    }

    private Map<String, String> getRequestParams(HttpServletRequest req) {
        Map<String, String> requestParams = new HashMap<String, String>();
        for(Enumeration<String> e=req.getParameterNames(); e.hasMoreElements();) {
            String parameterName = e.nextElement();
            String parameterValue = req.getParameter(parameterName);
            requestParams.put(parameterName, parameterValue);
        }
        //
        return requestParams;
    }

    @Override
    public List<ForkedRequestAndResponse> fork(HttpServletRequest req, HttpServletResponse res) {
        //
        PageConfig pageConfig = PageUtils.getPageConfig(req);
        //
        List<ForkedRequestAndResponse> forkedList = new ArrayList<ForkedRequestAndResponse>();
        for(WidgetConfig widgetConfig : pageConfig.getAllWidgetConfig()) {
            //
            String forkedName = widgetConfig.getId();
            String forkedPath = widgetConfig.getPath();
            //
            ForkedHttpServletRequest forkedReq = new ForkedHttpServletRequest(req, forkedPath);
            ForkedHttpServletResponse forkedRes = new ForkedHttpServletResponse(res);
            PageUtils.setWidgetConfig(forkedReq, widgetConfig);
            //
            forkedList.add(new ForkedRequestAndResponse(forkedName, forkedReq, forkedRes));
        }
        //
        return forkedList;
    }

}
