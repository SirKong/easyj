package net.hs.easyj.web.resource.xml;

import net.hs.easyj.web.resource.WebResource;
import net.hs.easyj.web.resource.WebResourceFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 基于 jaxb 的 Web 资源工厂实现
 *
 * @author <a href="mailto:huhz@hundsun.com">Gavin Hu</a>
 * @since 1.0.0
 */
public class JaxbWebResourceFactory implements WebResourceFactory {

    private Map<String, WebResource> webResourceMap = new HashMap<String, WebResource>();

    private Map<String, Map<String, WebResource>> nameVersionWebResourceMap = new HashMap<String, Map<String, WebResource>>() {
        @Override
        public Map<String, WebResource> get(Object name) {
            Map<String, WebResource> versionWebResourceMap = super.get(name);
            if(versionWebResourceMap==null) {
                versionWebResourceMap = new TreeMap<>();
                super.put((String)name, versionWebResourceMap);
            }
            return versionWebResourceMap;
        }
    };

    protected Enumeration<URL>  findWebResources() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if(classLoader==null) {
            classLoader = getClass().getClassLoader();
        }
        //
        return classLoader.getResources("META-INF/web-resources.xml");
    }

    @Override
    public WebResource load(String name, String version) {
        if(webResourceMap.isEmpty()) {
            synchronized (this) {
                if(webResourceMap.isEmpty()) {
                    doLoadAll();
                }
            }
        }
        //
        Map<String, WebResource> versionWebResourceMap = nameVersionWebResourceMap.get(name);
        if(nameVersionWebResourceMap.isEmpty()) {
            throw new IllegalArgumentException(String.format("Web Resource named %s not exists!", name));
        }
        //
        if(version==null) {
            version = versionWebResourceMap.keySet().iterator().next();
        }
        //
        WebResource webResource = versionWebResourceMap.get(version);
        if(webResource==null) {
            throw new IllegalArgumentException(String.format("Web Resource named %s with version %s not exists!", name, version));
        }
        //
        return webResource;
    }

    private void doLoadAll() {
        //
        try {
            Enumeration<URL> urlEnumeration = findWebResources();
            while(urlEnumeration.hasMoreElements()) {
                URL webResourceURL = urlEnumeration.nextElement();
                try(InputStream in = webResourceURL.openStream();) {
                    DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                    Document xmlDoc = documentBuilder.parse(in);
                    //
                    List<Element> webResourceEles = getChildElementsByTagName(xmlDoc.getDocumentElement(), new String[]{"web-resource"});
                    for (Element webResourceEle : webResourceEles) {
                        JAXBContext context = JAXBContext.newInstance(WebResource.class);
                        Unmarshaller unmarshaller = context.createUnmarshaller();
                        WebResource webResource = (WebResource) unmarshaller.unmarshal(webResourceEle);
                        //
                        Map<String, WebResource> versionMap = nameVersionWebResourceMap.get(webResource.getName());
                        versionMap.put(webResource.getVersion(), webResource);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Element> getChildElementsByTagName(Element ele, String[] childEleNames) {
        List<String> childEleNameList = Arrays.asList(childEleNames);
        NodeList nl = ele.getChildNodes();
        List<Element> childEles = new ArrayList<Element>();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element && nodeNameMatch(node, childEleNameList)) {
                childEles.add((Element) node);
            }
        }
        return childEles;
    }

    /** Matches the given node's name and local name against the given desired names. */
    private static boolean nodeNameMatch(Node node, Collection desiredNames) {
        return (desiredNames.contains(node.getNodeName()) || desiredNames.contains(node.getLocalName()));
    }

}
