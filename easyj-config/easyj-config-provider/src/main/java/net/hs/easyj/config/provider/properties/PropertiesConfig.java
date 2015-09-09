package net.hs.easyj.config.provider.properties;

import net.hs.easyj.config.spi.ObjectConfig;
import net.hs.easyj.config.spi.annotation.ConfigCategory;
import net.hs.easyj.config.spi.annotation.ConfigField;

/**
 * 属性配置
 *
 * @author Gavin Hu
 * @create 2015/7/22
 */
@ConfigCategory(value="properties", label="属性配置")
public class PropertiesConfig extends ObjectConfig {

    @ConfigField(description = "启用系统属性")
    private boolean enableSystemProperties;

    public PropertiesConfig() {
        super.setEnableDynamicProperties(true);
    }

    public boolean isEnableSystemProperties() {
        return enableSystemProperties;
    }

    public void setEnableSystemProperties(boolean enableSystemProperties) {
        this.enableSystemProperties = enableSystemProperties;
    }

}
