package net.hs.easyj.web.widget.annotation;

import org.springframework.stereotype.Controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author gavin
 * @create 15/8/20
 */
@Controller
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Widget {

    String value() default "";

}
