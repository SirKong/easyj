package net.hs.easyj.web.xdoc.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * XDoc 模型
 *
 * @author Gavin Hu
 * @create 2015/5/15
 */
@XmlRootElement(name = "xdoc")
@XmlAccessorType(XmlAccessType.FIELD)
public class XDoc implements Serializable {

    @XmlAttribute
    private String name;
    
    @XmlAttribute
    private String title;

    @XmlAttribute
    private String category;

    @XmlElement(name = "section")
    private List<XDocSection> sections = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<XDocSection> getSections() {
        return sections;
    }

    public void setSections(List<XDocSection> sections) {
        this.sections = sections;
    }

}
