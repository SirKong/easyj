package net.hs.easyj.web.widget.entry.config;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Widget 配置
 * TODO Performance Tuning
 * @author <a href="mailto:huhz@hundsun.com">Gavin Hu</a>
 * @since 1.0.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class WidgetConfig {
    private PositionConfig positionConfig;
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String mode;
    @XmlAttribute
    private String when;
    @XmlAttribute
    private String path;
    @XmlAttribute
    private String context;
    @XmlAttribute
    private int cache;
    // for order
    @XmlAttribute
    private int order;
    //
    @XmlElementWrapper(name = "props")
    @XmlElement(name = "prop")
    private List<PropConfig> propConfigs = new ArrayList<PropConfig>();
    @XmlElementWrapper(name = "events")
    @XmlElement(name = "event")
    private List<EventConfig> eventConfigs = new ArrayList<EventConfig>();
    //
    private Properties properties = new Properties();
    //
    public PositionConfig getPositionConfig() {
        return positionConfig;
    }

    public void setPositionConfig(PositionConfig positionConfig) {
        this.positionConfig = positionConfig;
    }

    public String getId() {
        if (id == null) {
            this.id = generateId();
        }
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getCache() {
        return cache;
    }

    public void setCache(int cache) {
        this.cache = cache;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Properties getProperties() {
        if(properties.isEmpty()) {
            for(PropConfig propConfig : propConfigs) {
                properties.setProperty(propConfig.getName(), propConfig.getValue());
            }
        }
        return properties;
    }

    public List<PropConfig> getPropConfigs() {
        return propConfigs;
    }

    public void setPropConfigs(List<PropConfig> propConfigs) {
        this.propConfigs = propConfigs;
    }

    public List<EventConfig> getEventConfigs() {
        return eventConfigs;
    }

    public void setEventConfigs(List<EventConfig> eventConfigs) {
        this.eventConfigs = eventConfigs;
    }

    private String generateId() {
        return new StringBuffer(positionConfig.getName()).append("_")
                .append(positionConfig.getWidgetConfigs().indexOf(this))
                .toString();
    }

}
