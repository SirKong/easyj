package net.hs.easyj.config.spi;

import net.hs.easyj.config.spi.annotation.ConfigField;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author gavin
 * @create 15/8/10
 */
public abstract class ObjectConfig implements Serializable {

    @ConfigField(description = "是否启用")
    private boolean enabled;

    private boolean enableDynamicProperties;

    protected Map<String, String> properties = new LinkedHashMap<>();

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnableDynamicProperties() {
        return enableDynamicProperties;
    }

    public void setEnableDynamicProperties(boolean enableDynamicProperties) {
        this.enableDynamicProperties = enableDynamicProperties;
    }

    public String getProperty(String name) {
        return this.properties.get(name);
    }

    public void setProperty(String name, String value) {
        this.properties.put(name, value);
    }

    public Map<String, String> getProperties() {
        return properties;
    }

}
