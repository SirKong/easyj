package net.hs.easyj.web.widget.handler.interceptor;

import net.hs.easyj.web.widget.handler.event.Event;
import net.hs.easyj.web.widget.handler.event.EventBus;
import net.hs.easyj.web.widget.entry.config.EventConfig;
import net.hs.easyj.web.widget.entry.config.PageConfig;
import net.hs.easyj.web.widget.entry.config.WidgetConfig;
import net.hs.easyj.web.widget.entry.utils.PageUtils;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 事件总线拦截器
 *
 * @author Gavin Hu
 * @create 2015/3/3
 */
public class WidgetInterceptor extends HandlerInterceptorAdapter {

    private ExpressionParser expressionParser = new ExpressionParser();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getRequestURI().endsWith(".do")) {
            String widgetId = request.getParameter("widgetId");
            PageConfig pageConfig = PageUtils.getPageConfig(request);
            WidgetConfig widgetConfig = pageConfig.getWidgetConfig(widgetId);
            // assert no null
            Assert.notNull(widgetConfig);
            EventBus eventBus = new EventBus(widgetConfig);
            //
            request.setAttribute(EventBus.class.getName(), eventBus);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //
        EventBus eventBus = (EventBus) request.getAttribute(EventBus.class.getName());
        if(eventBus!=null && eventBus.hasEvents()) {
            //
            Event event = eventBus.receive();
            WidgetConfig widgetConfig = eventBus.getWidgetConfig();
            //
            String redirectPath = getRedirectPathForEvent(widgetConfig, event);
            modelAndView.setViewName("redirect:" + redirectPath);
        }
    }

    protected String getRedirectPathForEvent(WidgetConfig widgetConfig, Event event) {
        for(EventConfig eventConfig : widgetConfig.getEventConfigs()) {
            if(event.getName().equals(eventConfig.getOn())) {
                String expressionString = eventConfig.getExpression();
                return expressionParser.parse(event.getData(), expressionString, String.class);
            }
        }
        //
        throw new NullPointerException(String.format("事件[%s]未定义！", event.getName()));
    }

    private class ExpressionParser {

        private SpelExpressionParser expressionParser = new SpelExpressionParser();

        private StandardEvaluationContext evaluationContext = new StandardEvaluationContext();

        private Map<String, Expression> expressionMap = new HashMap<String, Expression>();

        public ExpressionParser() {
            evaluationContext.addPropertyAccessor(new MapAccessor());
        }

        public <T> T parse(Object rootObject, String expressionString, Class<T> requiredType) {
            //
            Expression expression = expressionMap.get(expressionString);
            if(expression==null) {
                expression = expressionParser.parseExpression(expressionString, ParserContext.TEMPLATE_EXPRESSION);
                expressionMap.put(expressionString, expression);
            }
            //
            evaluationContext.setRootObject(rootObject);
            return expression.getValue(evaluationContext, requiredType);
        }

    }

}
