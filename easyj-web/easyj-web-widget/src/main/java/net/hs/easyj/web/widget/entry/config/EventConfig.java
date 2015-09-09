package net.hs.easyj.web.widget.entry.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * Event 配置
 *
 * @author <a href="mailto:huhz@hundsun.com">Gavin Hu</a>
 * @since 1.0.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class EventConfig {

    @XmlAttribute
    private String on;
    @XmlValue
    private String expression;

    public String getOn() {
        return on;
    }

    public void setOn(String on) {
        this.on = on;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
