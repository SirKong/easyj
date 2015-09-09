package net.hs.easyj.web.widget.handler.method.support;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Web扩展 参数解析器
 *
 * @author Gavin Hu
 * @create 2015/7/7
 */
public class WidgetArgumentResolver implements HandlerMethodArgumentResolver {

    private HandlerMethodArgumentResolverComposite argumentResolverComposite = new HandlerMethodArgumentResolverComposite();

    public WidgetArgumentResolver() {
        this.argumentResolverComposite.addResolver(new PageArgumentResolver());
        this.argumentResolverComposite.addResolver(new EventBusArgumentResolver());
        this.argumentResolverComposite.addResolver(new PageConfigArgumentResolver());
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return argumentResolverComposite.supportsParameter(parameter);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return argumentResolverComposite.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
    }

}
