package net.hs.easyj.web.widget.entry.config;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Page 配置
 *
 * @author <a href="mailto:huhz@hundsun.com">Gavin Hu</a>
 * @since 1.0.0
 */
@XmlRootElement(name = "page")
@XmlAccessorType(XmlAccessType.FIELD)
public class PageConfig {

    @XmlAttribute
    private String title;
    @XmlAttribute
    private String path;
    @XmlAttribute
    private String layout;
    @XmlAttribute
    private String parent;
    @XmlAttribute
    private String resources;
    //
    @XmlElement(name = "position")
    private List<PositionConfig> positionConfigs = new ArrayList<PositionConfig>();
    //
    private Map<String, WidgetConfig> widgetConfigMap = new HashMap<String, WidgetConfig>();

    public PageConfig() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String[] getResourceNames() {
        return resources==null ? new String[0] : resources.split(",");
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public List<PositionConfig> getPositionConfigs() {
        return positionConfigs;
    }

    public void setPositionConfigs(List<PositionConfig> positionConfigs) {
        this.positionConfigs = positionConfigs;
    }

    public List<WidgetConfig> getAllWidgetConfig() {
        if(widgetConfigMap.isEmpty()) {
            fillWidgetConfigMap();
        }
        return new ArrayList<WidgetConfig>(widgetConfigMap.values());
    }

    public boolean hasWidget(String widgetId) {
        if(widgetConfigMap.isEmpty()) {
            fillWidgetConfigMap();
        }
        return widgetConfigMap.containsKey(widgetId);
    }

    public WidgetConfig getWidgetConfig(String widgetId) {
        if(widgetConfigMap.isEmpty()) {
            fillWidgetConfigMap();
        }
        return widgetConfigMap.get(widgetId);
    }

    private void fillWidgetConfigMap() {
        for(PositionConfig positionConfig : positionConfigs) {
            for(WidgetConfig widgetConfig : positionConfig.getWidgetConfigs()) {
                this.widgetConfigMap.put(widgetConfig.getId(), widgetConfig);
            }
        }
    }

}
