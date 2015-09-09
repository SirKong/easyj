package net.hs.easyj.web.xdoc.scanner;

import net.hs.easyj.web.xdoc.model.XDoc;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * 默认的 XDoc 扫描器实现
 *
 * @author Gavin Hu
 * @create 2015/5/15
 */
public class XDocPatternScanner implements XDocScanner, ResourceLoaderAware {

    public static final String[] XDOC_PATTERNS = new String[]{"classpath*:META-INF/**/*XDoc.xml"};

    private String[] patterns = XDOC_PATTERNS;

    private ResourcePatternResolver resourcePatternResolver;

    public void setPattern(String pattern) {
        this.patterns = new String[]{pattern};
    }

    public void setPatterns(String[] patterns) {
        this.patterns = patterns;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
    }

    @Override
    public Map<String, XDoc> scan() {
        //
        Map<String, XDoc> xDocMap = new HashMap<>();
        //
        for(String pattern : patterns) {
            try {
                //
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                //
                Resource[] xdocResources = resourcePatternResolver.getResources(pattern);
                for(Resource xDocResource : xdocResources) {
                    //
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document xml = db.parse(xDocResource.getInputStream());
                    //
                    JAXBContext context = JAXBContext.newInstance(XDoc.class);
                    Unmarshaller unmarshaller = context.createUnmarshaller();
                    XDoc xDoc = (XDoc) unmarshaller.unmarshal(xml);
                    //
                    xDocMap.put(xDoc.getName(), xDoc);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //
        return xDocMap;
    }

}
