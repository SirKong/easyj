package net.hs.easyj.web.widget.annotation;

import net.hs.easyj.web.widget.handler.interceptor.WidgetInterceptor;
import net.hs.easyj.web.widget.handler.method.WidgetRequestMappingHandlerMapping;
import net.hs.easyj.web.widget.handler.method.support.WidgetArgumentResolver;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultBeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Widget Mvc 后处理器
 *
 * @author Gavin Hu
 * @create 2015/8/25
 */
public class WidgetMvcPostProcessor implements BeanDefinitionRegistryPostProcessor {

    private BeanNameGenerator beanNameGenerator = new DefaultBeanNameGenerator();

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //
        registerWidgetRequestMappingHandlerMapping(registry);
        //
        hackRequestMappingHandlerAdapter(registry);
        //
        registerWidgetInterceptors(registry);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    private void hackRequestMappingHandlerAdapter(BeanDefinitionRegistry registry) {
        // custom argument resolvers
        List<BeanDefinition> requestMappingHandlerAdapterDefinitions = findBeanDefinitionsForClass(registry, RequestMappingHandlerAdapter.class.getName());
        for(BeanDefinition beanDefinition : requestMappingHandlerAdapterDefinitions) {
            //
            List<HandlerMethodArgumentResolver> customArgumentResolvers = getCustomArgumentResolvers();
            PropertyValue customArgumentResolversPropertyValue = beanDefinition.getPropertyValues().getPropertyValue("customArgumentResolvers");
            if(customArgumentResolversPropertyValue==null) {
                beanDefinition.getPropertyValues().add("customArgumentResolvers", customArgumentResolvers);
            } else {
                List<HandlerMethodArgumentResolver> originalCustomArgumentResolvers = (List<HandlerMethodArgumentResolver>) customArgumentResolversPropertyValue.getValue();
                originalCustomArgumentResolvers.addAll(customArgumentResolvers);
            }
        }
    }

    private List<HandlerMethodArgumentResolver> getCustomArgumentResolvers() {
        List<HandlerMethodArgumentResolver> customArgumentResolvers = new ArrayList<>();
        customArgumentResolvers.add(new WidgetArgumentResolver());
        return customArgumentResolvers;
    }

    private void registerWidgetRequestMappingHandlerMapping(BeanDefinitionRegistry registry) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(WidgetRequestMappingHandlerMapping.class);
        beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        String beanName = beanNameGenerator.generateBeanName(beanDefinition, registry);
        registry.registerBeanDefinition(beanName, beanDefinition);
    }

    private void registerWidgetInterceptors(BeanDefinitionRegistry registry) {
        registerWidgetInterceptor(registry, null, null, WidgetInterceptor.class);
    }

    private void registerWidgetInterceptor(BeanDefinitionRegistry registry, String[] includePatterns, String[] excludePatterns, Class interceptorClass) {
        //
        RootBeanDefinition interceptorDefinition = new RootBeanDefinition(interceptorClass);
        String interceptorBeanName = beanNameGenerator.generateBeanName(interceptorDefinition, registry);
        registry.registerBeanDefinition(interceptorBeanName, interceptorDefinition);
        //
        RootBeanDefinition mappedInterceptorDefinition = new RootBeanDefinition(MappedInterceptor.class);
        mappedInterceptorDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, includePatterns);
        mappedInterceptorDefinition.getConstructorArgumentValues().addIndexedArgumentValue(1, excludePatterns);
        mappedInterceptorDefinition.getConstructorArgumentValues().addIndexedArgumentValue(2, interceptorDefinition);
        String mappedInterceptorBeanName = beanNameGenerator.generateBeanName(mappedInterceptorDefinition, registry);
        registry.registerBeanDefinition(mappedInterceptorBeanName, mappedInterceptorDefinition);
    }

    private List<BeanDefinition> findBeanDefinitionsForClass(BeanDefinitionRegistry registry, String className) {
        List<BeanDefinition> beanDefinitions = new ArrayList<>();
        for(String beanDefinitionName : registry.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = registry.getBeanDefinition(beanDefinitionName);
            if(beanDefinition.getBeanClassName().equals(RequestMappingHandlerAdapter.class.getName())) {
                //
                beanDefinitions.add(beanDefinition);
            }
        }
        return beanDefinitions;
    }

}
