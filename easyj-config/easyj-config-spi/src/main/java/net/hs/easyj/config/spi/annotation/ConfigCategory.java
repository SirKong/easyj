package net.hs.easyj.config.spi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 配置分类
 *
 * @author Gavin Hu
 * @create 2015/7/22
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigCategory {

    /**
     * 分类值
     * @return
     */
    String value();

    /**
     * 分类显示名
     * @return
     */
    String label();

}
