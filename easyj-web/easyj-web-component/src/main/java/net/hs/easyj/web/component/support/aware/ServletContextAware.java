package net.hs.easyj.web.component.support.aware;

import javax.servlet.ServletContext;

/**
 * ServletContext 注入
 *
 * @author Gavin Hu
 * @create 2015/5/25
 */
public interface ServletContextAware {

    void setServletContext(ServletContext context);

}
