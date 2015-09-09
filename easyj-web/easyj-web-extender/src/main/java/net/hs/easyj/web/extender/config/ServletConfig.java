package net.hs.easyj.web.extender.config;

import java.util.*;

/**
 * Created by Gavin Hu on 2015/2/7.
 */
public class ServletConfig {

    private String servletName;

    private String servletClass;

    private List<String> urlPatterns = new ArrayList<String>();

    private Hashtable<String, String> initParamMap = new Hashtable<String, String>();

    public ServletConfig(String servletName, String servletClass) {
        this.servletName = servletName;
        this.servletClass = servletClass;
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public String getServletClass() {
        return servletClass;
    }

    public void setServletClass(String servletClass) {
        this.servletClass = servletClass;
    }

    public List<String> getUrlPatterns() {
        return urlPatterns;
    }

    public Hashtable<String, String> getInitParams() {
        return initParamMap;
    }

}
