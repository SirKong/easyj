package net.hs.easyj.web.widget.handler.method.support;

import net.hs.easyj.model.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Page 参数解析器
 *
 * @author Gavin Hu
 * @creat 2015/3/11
 */
public class PageArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return ClassUtils.isAssignable(Page.class, parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //
        Page<?> page = (Page<?>) BeanUtils.instantiateClass(parameter.getParameterType());
        String pageNo = webRequest.getParameter("pageNo");
        if(StringUtils.hasText(pageNo)) {
            page.setPageNo(Integer.parseInt(pageNo));
        }
        String pageSize = webRequest.getParameter("pageSize");
        if(StringUtils.hasText(pageSize)) {
            page.setPageSize(Integer.parseInt(pageSize));
        } else {
            page.setPageSize(new Integer(10));
        }
        String totalCount = webRequest.getParameter("totalCount");
        if(StringUtils.hasText(totalCount)) {
            page.setAutoCount(false);
            page.setTotalCount(Integer.parseInt(totalCount));
        }
        return page;
    }
}
