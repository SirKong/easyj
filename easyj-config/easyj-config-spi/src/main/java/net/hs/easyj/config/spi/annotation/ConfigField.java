package net.hs.easyj.config.spi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 配置字段
 *
 * @author Gavin Hu
 * @create 2015/7/23
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigField {

    /**
     * 字段是否必填
     * @return
     */
    boolean required() default false;

    /**
     * 字段描述
     * @return
     */
    String description();

}
