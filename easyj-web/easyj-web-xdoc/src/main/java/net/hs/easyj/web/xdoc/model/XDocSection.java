package net.hs.easyj.web.xdoc.model;

import org.springframework.util.StringUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * XDoc 片段
 *
 * @author Gavin Hu
 * @create 2015/5/15
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class XDocSection {

    public static final String TYPE_HTML = "html";

    public static final String TYPE_CODE = "code";

    public static final String TYPE_LINK = "link";

    @XmlAttribute
    private String title;

    @XmlAttribute
    private String type = TYPE_HTML;

    @XmlAttribute
    private String location;

    @XmlValue
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContent() {
        return content;
    }

    public Object getContent(String type) {
        if(TYPE_LINK.equals(type)) {
            String[] links = StringUtils.delimitedListToStringArray(content.trim(), "\n", "\r ");
            Set<String> linkSet = new LinkedHashSet<>();
            for(String link : links) {
                if(link==null || "".equals(link.trim())) {
                    continue;
                }
                linkSet.add(link.trim());
            }
            return linkSet;
        }
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
