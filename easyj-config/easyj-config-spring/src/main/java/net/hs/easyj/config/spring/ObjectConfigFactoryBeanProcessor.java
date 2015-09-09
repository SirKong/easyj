package net.hs.easyj.config.spring;

import net.hs.easyj.zk.ZkConnection;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * 对象配置工厂后处理器
 *
 * @author Gavin Hu
 * @create 2015/8/14
 */
public class ObjectConfigFactoryBeanProcessor implements BeanPostProcessor {

    private static final Field FIELD_SYSTEM = ReflectionUtils.findField(ObjectConfigFactoryBean.class, "system");

    private static final Field FIELD_PROFILE = ReflectionUtils.findField(ObjectConfigFactoryBean.class, "profile");

    private String system;

    private String profile;

    private ZkConnection zkConnection;

    public void setSystem(String system) {
        this.system = system;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Autowired
    public void setZkConnection(ZkConnection zkConnection) {
        this.zkConnection = zkConnection;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof ObjectConfigFactoryBean) {
            ObjectConfigFactoryBean factoryBean = (ObjectConfigFactoryBean) bean;
            // make accessible
            ReflectionUtils.makeAccessible(FIELD_SYSTEM);
            ReflectionUtils.makeAccessible(FIELD_PROFILE);
            //
            if(ReflectionUtils.getField(FIELD_SYSTEM, factoryBean)==null) {
                factoryBean.setSystem(system);
            }
            if(ReflectionUtils.getField(FIELD_PROFILE, factoryBean)==null) {
                ((ObjectConfigFactoryBean)bean).setProfile(profile);
            }
            ((ObjectConfigFactoryBean)bean).setZkConnection(zkConnection);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
