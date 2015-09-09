package net.hs.easyj.web.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Web 资源集
 *
 * @author <a href="mailto:huhz@hundsun.com">Gavin Hu</a>
 * @since 1.0.0
 */
public class WebResources {

    private WebResourceFactory webResourceFactory;

    private Map<String, WebResource> webResourceMap = new LinkedHashMap<String, WebResource>();
    //
    public WebResources(WebResourceFactory webResourceFactory) {
        this.webResourceFactory = webResourceFactory;
    }

    public void addWebResources(String[] webResourceNames) {
        //
        for(String webResourceName : webResourceNames) {
            //
            WebResource webResource = webResourceFactory.load(webResourceName, null);
            addDependWebResource(webResource);
            //
            webResourceMap.put(webResourceName, webResource);
        }
    }

    protected void addDependWebResource(WebResource webResource) {
        //
        Map<String, String> dependencies = webResource.getDependencies();
        for(String name : dependencies.keySet()){
            String version = dependencies.get(name);
            WebResource dependedWebResource = webResourceFactory.load(name, version);
            addDependWebResource(dependedWebResource);
            //
            webResourceMap.put(dependedWebResource.getName(), dependedWebResource);
        }
    }

    public List<String> getJavaScripts() {
        List<String> javaScripts = new ArrayList<String>();
        //
        for(WebResource webResource : webResourceMap.values()) {
            javaScripts.addAll(webResource.getJavaScripts());
        }
        //
        return javaScripts;
    }

    public List<String> getStyleSheets() {
        List<String> styleSheets = new ArrayList<String>();
        //
        for(WebResource webResource : webResourceMap.values()) {
            styleSheets.addAll(webResource.getStyleSheets());
        }
        //
        return styleSheets;
    }

}
