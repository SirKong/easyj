package net.hs.easyj.web.widget.handler.method;


import net.hs.easyj.web.widget.annotation.ActionMapping;
import net.hs.easyj.web.widget.annotation.DataMapping;
import net.hs.easyj.web.widget.annotation.ViewMapping;
import net.hs.easyj.web.widget.annotation.Widget;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.ConsumesRequestCondition;
import org.springframework.web.servlet.mvc.condition.HeadersRequestCondition;
import org.springframework.web.servlet.mvc.condition.ParamsRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import java.lang.reflect.Method;

/**
 * Widget view 和 action 映射处理器
 *
 * @author <a href="mailto:huhz@hundsun.com">Gavin Hu</a>
 * @since 1.0.0
 */
public class WidgetRequestMappingHandlerMapping extends RequestMappingInfoHandlerMapping {

    private boolean useSuffixPatternMatch = true;

    private boolean useTrailingSlashMatch = true;

    public void setUseSuffixPatternMatch(boolean useSuffixPatternMatch) {
        this.useSuffixPatternMatch = useSuffixPatternMatch;
    }

    public void setUseTrailingSlashMatch(boolean useTrailingSlashMatch) {
        this.useTrailingSlashMatch = useTrailingSlashMatch;
    }

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
    }

    @Override
    protected boolean isHandler(Class<?> beanType) {
        return AnnotationUtils.findAnnotation(beanType, Widget.class) != null;
    }

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        //
        RequestMappingInfo requestMappingInfo = null;
        ViewMapping viewMapping = AnnotationUtils.findAnnotation(method, ViewMapping.class);
        DataMapping restMapping = AnnotationUtils.findAnnotation(method, DataMapping.class);
        ActionMapping actionMapping = AnnotationUtils.findAnnotation(method, ActionMapping.class);
        //
        if(viewMapping!=null) {
            // create view request mapping info
            requestMappingInfo = createViewRequestMappingInfo(viewMapping);
        } else if(restMapping!=null) {
            // create rest request mapping info
            requestMappingInfo = createRestRequestMappingInfo(restMapping);
        } else if(actionMapping!=null) {
            // create action request mapping info
            requestMappingInfo = createActionRequestMappingInfo(actionMapping);
        }
        //
        return requestMappingInfo;
    }

    private RequestMappingInfo createViewRequestMappingInfo(ViewMapping annotation) {
        return new RequestMappingInfo(
                new PatternsRequestCondition(annotation.value(),
                        getUrlPathHelper(), getPathMatcher(), this.useSuffixPatternMatch, this.useTrailingSlashMatch),
                new RequestMethodsRequestCondition(annotation.method()),
                new ParamsRequestCondition(annotation.params()),
                new HeadersRequestCondition(new String[0]),
                new ConsumesRequestCondition(new String[0], new String[0]),
                new ProducesRequestCondition(new String[0], new String[0]),
                null);
    }

    private RequestMappingInfo createActionRequestMappingInfo(ActionMapping annotation) {
        return new RequestMappingInfo(
                new PatternsRequestCondition(annotation.value(),
                        getUrlPathHelper(), getPathMatcher(), this.useSuffixPatternMatch, this.useTrailingSlashMatch),
                new RequestMethodsRequestCondition(annotation.method()),
                new ParamsRequestCondition(annotation.params()),
                new HeadersRequestCondition(new String[0]),
                new ConsumesRequestCondition(new String[0], new String[0]),
                new ProducesRequestCondition(new String[0], new String[0]),
                null);
    }

    private RequestMappingInfo createRestRequestMappingInfo(DataMapping annotation) {
        return new RequestMappingInfo(
                new PatternsRequestCondition(annotation.value(),
                        getUrlPathHelper(), getPathMatcher(), this.useSuffixPatternMatch, this.useTrailingSlashMatch),
                new RequestMethodsRequestCondition(annotation.method()),
                new ParamsRequestCondition(annotation.params()),
                new HeadersRequestCondition(new String[0]),
                new ConsumesRequestCondition(new String[0], new String[0]),
                new ProducesRequestCondition(new String[0], new String[0]),
                null);
    }

}
