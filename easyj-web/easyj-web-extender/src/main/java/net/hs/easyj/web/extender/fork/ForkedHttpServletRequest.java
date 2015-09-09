package net.hs.easyj.web.extender.fork;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

/**
 * 拆分的 HttpServletRequest
 *
 * @author Gavin Hu
 * @create 2015/2/7
 */
public class ForkedHttpServletRequest extends HttpServletRequestWrapper {

    private String dispatchPath;

    private Map parameterMap;

    private Map<String, Object> attributeMap = new HashMap<String, Object>();

    public ForkedHttpServletRequest(HttpServletRequest request, String dispatchPath) {
        super(request);
        this.dispatchPath = dispatchPath;
        this.parameterMap = newParameterMap(request);
    }

    private Map newParameterMap(HttpServletRequest request) {
        return new HashMap(request.getParameterMap());
    }

    @Override
    public Map getParameterMap() {
        return parameterMap;
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return super.getRequestDispatcher(dispatchPath);
    }

    @Override
    public Object getAttribute(String name) {
        if(attributeMap.containsKey(name)) {
            return attributeMap.get(name);
        }
        return super.getAttribute(name);
    }

    @Override
    public void setAttribute(String name, Object value) {
        this.attributeMap.put(name, value);
    }

    @Override
    public String getRequestURI() {
        return getContextPath() + dispatchPath;
    }

    @Override
    public String getPathInfo() {
        return dispatchPath;
    }
}
