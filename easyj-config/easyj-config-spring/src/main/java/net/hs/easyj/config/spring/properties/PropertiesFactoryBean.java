package net.hs.easyj.config.spring.properties;

import net.hs.easyj.config.provider.properties.PropertiesConfig;
import net.hs.easyj.config.provider.properties.PropertiesCreator;
import net.hs.easyj.config.provider.properties.PropertiesDelegate;
import net.hs.easyj.config.spring.ObjectConfigFactoryBean;

import java.util.Properties;

/**
 * 属性工厂类
 *
 * @author Gavin Hu
 * @create 2015/8/12
 */
public class PropertiesFactoryBean extends ObjectConfigFactoryBean<Properties> {

    public PropertiesFactoryBean() {
        super.setCategory("properties");
        //
        super.setObjectType(Properties.class);
        super.setObjectCreator(new PropertiesCreator());
        super.setObjectDelegate(new PropertiesDelegate());
        super.setObjectConfigClass(PropertiesConfig.class);
    }

}
