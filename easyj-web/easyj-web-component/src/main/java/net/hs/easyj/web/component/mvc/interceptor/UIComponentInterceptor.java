package net.hs.easyj.web.component.mvc.interceptor;

import net.hs.easyj.web.component.engine.UIContext;
import net.hs.easyj.web.component.engine.UIEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 基于 Spring MVC UI 组件拦截器
 *
 * @author Gavin Hu
 * @create 2015/5/8
 */
public class UIComponentInterceptor extends HandlerInterceptorAdapter {

    private UIEngine uiEngine;

    @Autowired
    public void setUiEngine(UIEngine uiEngine) {
        this.uiEngine = uiEngine;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //
        if(modelAndView!=null && modelAndView.hasView()) {
            String viewName = modelAndView.getViewName();
            //
            UIContext uiContext = new UIContext(viewName);
            uiContext.setObject(HttpServletRequest.class.getName(), request);
            uiContext.setObject(ServletContext.class.getName(), request.getSession().getServletContext());
            //
            uiEngine.run(uiContext);
            //
            request.setAttribute(UIContext.class.getName(), uiContext);
        }
    }

}
