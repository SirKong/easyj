package net.hs.easyj.web.widget.entry.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Position 配置
 *
 * @author <a href="mailto:huhz@hundsun.com">Gavin Hu</a>
 * @since 1.0.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PositionConfig {
    //
    private PageConfig pageConfig;
    //
    @XmlAttribute(name = "name")
    private String name;
    @XmlElement(name = "widget")
    private List<WidgetConfig> widgetConfigs = new ArrayList<WidgetConfig>();
    @XmlAttribute(name = "layout")
    private String layout;

    public PageConfig getPageConfig() {
        return pageConfig;
    }

    public void setPageConfig(PageConfig pageConfig) {
        this.pageConfig = pageConfig;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WidgetConfig> getWidgetConfigs() {
        return widgetConfigs;
    }

    public void setWidgetConfigs(List<WidgetConfig> widgetConfigs) {
        this.widgetConfigs = widgetConfigs;
    }

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

}
