package net.hs.easyj.config.provider.properties;

import net.hs.easyj.config.spi.ObjectCreator;

import java.util.Properties;

/**
 * 属性对象创建器
 *
 * @author Gavin Hu
 * @create 2015/7/22
 */
public class PropertiesCreator implements ObjectCreator<PropertiesConfig, Properties>{

    @Override
    public Properties create(PropertiesConfig config) {
        Properties properties = new Properties();
        properties.putAll(config.getProperties());
        if(config.isEnableSystemProperties()) {
            properties.putAll(System.getProperties());
        }
        return properties;
    }

}
