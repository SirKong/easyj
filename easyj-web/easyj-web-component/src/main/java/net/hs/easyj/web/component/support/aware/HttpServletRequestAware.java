package net.hs.easyj.web.component.support.aware;

import javax.servlet.http.HttpServletRequest;

/**
 * HttpServletRequest 注入
 *
 * @author Gavin Hu
 * @create 2015/5/25
 */
public interface HttpServletRequestAware {

    void setHttpServletRequest(HttpServletRequest request);

}
