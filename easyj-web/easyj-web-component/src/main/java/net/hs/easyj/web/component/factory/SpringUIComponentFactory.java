package net.hs.easyj.web.component.factory;

import net.hs.easyj.web.component.UIComponent;
import net.hs.easyj.web.component.exception.NoSuchUIComponentException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StringUtils;

/**
 * 基于 Spring 容器的 UI 组件工厂
 *
 * @author Gavin Hu
 * @create 2015/5/6
 */
public class SpringUIComponentFactory implements UIComponentFactory, ApplicationContextAware, InitializingBean {

    private static final String[] DEFAULT_CONFIG_LOCATIONS = new String[]{
            "classpath*:META-INF/web-components.xml"
    };

    private String[] configLocations = DEFAULT_CONFIG_LOCATIONS;

    private ApplicationContext applicationContext;

    private GenericXmlApplicationContext uiComponentContext;

    public void setConfigLocations(String[] configLocations) {
        this.configLocations = configLocations;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.uiComponentContext = new GenericXmlApplicationContext();
        this.uiComponentContext.setParent(applicationContext);
        this.uiComponentContext.setResourceLoader(applicationContext);
        this.uiComponentContext.load(configLocations);
        this.uiComponentContext.refresh();
    }

    @Override
    public UIComponent get(String name) {
        try {
            String beanName = StringUtils.uncapitalize(name);
            return this.uiComponentContext.getBean(beanName, UIComponent.class);
        } catch (BeansException e) {
            throw new NoSuchUIComponentException(name, e);
        }
    }

}
