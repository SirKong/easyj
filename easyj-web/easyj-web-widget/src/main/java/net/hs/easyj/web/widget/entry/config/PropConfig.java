package net.hs.easyj.web.widget.entry.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * 属性配置
 *
 * @author Gavin Hu
 * @create
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PropConfig {

    @XmlAttribute
    private String name;
    @XmlValue
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
