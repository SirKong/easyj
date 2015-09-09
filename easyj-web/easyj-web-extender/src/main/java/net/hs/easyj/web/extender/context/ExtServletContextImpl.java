package net.hs.easyj.web.extender.context;

import javax.servlet.*;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by Gavin Hu on 2015/2/6.
 */
public class ExtServletContextImpl implements ExtServletContext {

    private ServletContext servletContext;

    private ServletContextAttributeListener attributeListener;

    private Map<String, Object> attributes = new HashMap<String, Object>();

    private Map<String, String> contextParams = new HashMap<String, String>();

    public ExtServletContextImpl(ServletContext servletContext, ServletContextAttributeListener attributeListener) {
        this.servletContext = servletContext;
        this.attributeListener = attributeListener;
    }

    public ExtServletContextImpl(ServletContext servletContext) {
        ExtServletContextImpl impl = (ExtServletContextImpl) servletContext;
        this.servletContext = impl.servletContext;
        this.attributeListener = impl.attributeListener;
    }

    @Override
    public void setContextParams(Map<String, String> contextParams) {
        this.contextParams.putAll(contextParams);
    }

    @Override
    public String getContextPath() {
        return servletContext.getContextPath();
    }

    @Override
    public ServletContext getContext(String uripath) {
        return servletContext.getContext(uripath);
    }

    @Override
    public int getMajorVersion() {
        return servletContext.getMajorVersion();
    }

    @Override
    public int getMinorVersion() {
        return servletContext.getMinorVersion();
    }

    @Override
    public String getMimeType(String file) {
        return servletContext.getMimeType(file);
    }

    @Override
    public Set<String> getResourcePaths(String path) {
        return servletContext.getResourcePaths(path);
    }

    @Override
    public URL getResource(String path) throws MalformedURLException {
        return servletContext.getResource(path);
    }

    @Override
    public InputStream getResourceAsStream(String path) {
        return servletContext.getResourceAsStream(path);
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return servletContext.getRequestDispatcher(path);
    }

    @Override
    public RequestDispatcher getNamedDispatcher(String name) {
        return servletContext.getNamedDispatcher(name);
    }

    @Override
    public Servlet getServlet(String name) throws ServletException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Enumeration<Servlet> getServlets() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Enumeration<String> getServletNames() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void log(String msg) {
        servletContext.log(msg);
    }

    @Override
    public void log(Exception exception, String msg) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void log(String message, Throwable throwable) {
        servletContext.log(message, throwable);
    }

    @Override
    public String getRealPath(String path) {
        return servletContext.getRealPath(path);
    }

    @Override
    public String getServerInfo() {
        return servletContext.getServerInfo();
    }

    @Override
    public String getInitParameter(String name) {
        String value = contextParams.get(name);
        if(value == null) {
            value = servletContext.getInitParameter(name);
        }
        //
        return value;
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        Vector<String> initParamNamesVector = new Vector<String>(contextParams.keySet());
        for(Enumeration<String> e=servletContext.getInitParameterNames(); e.hasMoreElements();) {
            initParamNamesVector.add(e.nextElement());
        }
        return initParamNamesVector.elements();
    }

    @Override
    public Object getAttribute(String name) {
        Object value = attributes.get(name);
        if(value==null) {
            value = servletContext.getAttribute(name);
        }
        //
        return value;
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        Vector<String> attrNameVector = new Vector<String>(attributes.keySet());
        for(Enumeration<String> e=servletContext.getAttributeNames(); e.hasMoreElements();) {
            attrNameVector.add(e.nextElement());
        }
        return attrNameVector.elements();
    }

    @Override
    public void setAttribute(String name, Object object) {
        this.attributes.put(name, object);
        //
        if(attributeListener!=null) {
            ServletContextAttributeEvent scab = new ServletContextAttributeEvent(this, name, object);
            attributeListener.attributeAdded(scab);
        }
    }

    @Override
    public void removeAttribute(String name) {
        Object removedValue = attributes.remove(name);
        if(removedValue==null) {
            removedValue = servletContext.getAttribute(name);
            servletContext.removeAttribute(name);
        }
        //
        if(attributeListener!=null && removedValue!=null) {
            ServletContextAttributeEvent scab = new ServletContextAttributeEvent(this, name, removedValue);
            attributeListener.attributeRemoved(scab);
        }
    }

    @Override
    public String getServletContextName() {
        return servletContext.getServletContextName();
    }


}
