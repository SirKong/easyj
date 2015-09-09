package net.hs.easyj.web.widget.annotation;

import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author gavin
 * @create 15/8/20
 */
@Mapping
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionMapping {

    /**
     * Action 请求路径 支持 ant 路径表达式
     *
     * @return
     */
    String[] value() default {};

    /**
     * Http 请求方法  POST GET PUT DELETE ...
     *
     * @return
     */
    RequestMethod[] method() default {RequestMethod.POST};

    /**
     * 请求参数表达式  例如 name!=value or name=value
     *
     * @return
     */
    String[] params() default {};

}
