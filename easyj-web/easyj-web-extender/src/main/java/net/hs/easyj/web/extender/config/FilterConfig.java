package net.hs.easyj.web.extender.config;

import java.util.*;

/**
 * Created by Gavin Hu on 2015/2/7.
 */
public class FilterConfig {

    private String filterName;

    private String filterClass;

    private String servletName;

    private List<String> urlPatterns = new ArrayList<String>();

    private Hashtable<String, String> initParamMap = new Hashtable<String, String>();

    public FilterConfig(String filterName, String filterClass) {
        this.filterName = filterName;
        this.filterClass = filterClass;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getFilterClass() {
        return filterClass;
    }

    public void setFilterClass(String filterClass) {
        this.filterClass = filterClass;
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public List<String> getUrlPatterns() {
        return urlPatterns;
    }

    public Hashtable<String, String> getInitParams() {
        return initParamMap;
    }

}
