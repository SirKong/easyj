package net.hs.easyj.web.resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Web 资源集
 *
 * @author <a href="mailto:huhz@hundsun.com">Gavin Hu</a>
 * @create 2013-12-15
 */
@XmlRootElement(name = "web-resource")
@XmlAccessorType(XmlAccessType.FIELD)
public class WebResource {
    private static final Pattern PATTERN_DEPENDENCY = Pattern.compile("(.*?)\\((.*?)\\)");

    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "version")
    private String version;
    @XmlAttribute(name = "depends")
    private String depends;
    //
    @XmlElement(name = "font")
    private List<String> fonts = new ArrayList<String>();
    @XmlElement(name = "css")
    private List<String> styleSheets = new ArrayList<String>();
    @XmlElement(name = "js")
    private List<String> javaScripts = new ArrayList<String>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        if(version==null) {
            this.version = "#version#";
        }
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, String> getDependencies() {
        Map<String, String> dependencies = new LinkedHashMap<>();
        if(depends!=null) {
            String[] parts = depends.split(",");
            for(String part : parts) {
                Matcher matcher = PATTERN_DEPENDENCY.matcher(part);
                if(matcher.find()) {
                    String name = matcher.group(1);
                    String version = matcher.group(2);
                    //
                    dependencies.put(name, version);
                } else {
                    dependencies.put(part, null);
                }
            }
        }
        //
        return dependencies;
    }

    public String getDepends() {
        return depends;
    }

    public void setDepends(String depends) {
        this.depends = depends;
    }

    public List<String> getFonts() {
        return fonts;
    }

    public void setFonts(List<String> fonts) {
        this.fonts = fonts;
    }

    public List<String> getStyleSheets() {
        return styleSheets;
    }

    public void setStyleSheets(List<String> styleSheets) {
        this.styleSheets = styleSheets;
    }

    public List<String> getJavaScripts() {
        return javaScripts;
    }

    public void setJavaScripts(List<String> javaScripts) {
        this.javaScripts = javaScripts;
    }
}
