package net.hs.easyj.web.widget.view.thymeleaf;

import net.hs.easyj.web.widget.view.thymeleaf.processor.standard.StandardActionAttrProcessor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.standard.StandardDialect;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Spring 模板引擎处理器
 *
 * @author Gavin Hu
 * @create 2015/7/7
 */
public class SpringTemplateEnginePostProcessor implements BeanPostProcessor, InitializingBean {

    private Set<IProcessor> additionalStandardProcessors = new LinkedHashSet<>();

    public void setAdditionalStandardProcessors(Set<IProcessor> additionalStandardProcessors) {
        this.additionalStandardProcessors.addAll(additionalStandardProcessors);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof SpringTemplateEngine) {
            processBeforeInitialization((SpringTemplateEngine) bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof SpringTemplateEngine) {
            processAfterInitialization((SpringTemplateEngine) bean);
        }
        return bean;
    }

    protected void processBeforeInitialization(SpringTemplateEngine templateEngine) {
        //
        for(IDialect dialect : templateEngine.getDialects()) {
            if(dialect instanceof StandardDialect) {
                ((StandardDialect) dialect).setAdditionalProcessors(additionalStandardProcessors);
            }
        }
    }

    protected void processAfterInitialization(SpringTemplateEngine templateEngine) {
        //
        //System.out.println(templateEngine);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        this.additionalStandardProcessors.add(new StandardActionAttrProcessor());
    }
}
