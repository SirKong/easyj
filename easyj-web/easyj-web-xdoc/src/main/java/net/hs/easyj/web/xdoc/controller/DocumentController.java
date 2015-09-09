package net.hs.easyj.web.xdoc.controller;

import net.hs.easyj.web.component.engine.UIContext;
import net.hs.easyj.web.component.engine.UIEngine;
import net.hs.easyj.web.component.loader.UIComponentDataLoader;
import net.hs.easyj.web.component.resource.ClassLoaderUIComponentResourceLoader;
import net.hs.easyj.web.component.resource.UIComponentResource;
import net.hs.easyj.web.component.resource.UIComponentResourceLoader;
import net.hs.easyj.web.xdoc.model.XDoc;
import net.hs.easyj.web.xdoc.scanner.XDocScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文档控制器
 *
 * @author Gavin Hu
 * @create 2015/5/15
 */
@Controller
public class DocumentController {

    private String themeName = "default";

    private String skinName = "metro";

    @Autowired
    private XDocScanner xDocScanner;

    @Autowired
    private UIEngine uiEngine;

    private UIComponentDataLoader uiComponentDataLoader;

    @RequestMapping("/")
    public String show(Model model) {
        //
        Map<String, XDoc> xDocMap = xDocScanner.scan();
        Map<String, List<XDoc>> organizedXDocMap = organizeByCategory(xDocMap);
        model.addAttribute("xDocMap", organizedXDocMap);
        //
        return themeName + "/layout_xdoc";
    }

    private Map<String, List<XDoc>> organizeByCategory(Map<String, XDoc> xDocMap) {
        //
        Map<String, List<XDoc>> organizedXDocMap = new HashMap<>();
        for(XDoc xDoc : xDocMap.values()) {
            List<XDoc> xDocList = organizedXDocMap.get(xDoc.getCategory());
            if(xDocList==null) {
                xDocList = new ArrayList<>();
                organizedXDocMap.put(xDoc.getCategory(), xDocList);
            }
            xDocList.add(xDoc);
        }
        return organizedXDocMap;
    }

    @RequestMapping("/show/sections")
    public String showSections(@RequestParam String name, Model model) {
        //
        Map<String, XDoc> xDocMap = xDocScanner.scan();
        XDoc xDoc = xDocMap.get(name);
        model.addAttribute("xDoc", xDoc);
        //
        return themeName + "/layout_xdoc_sections";
    }

    @RequestMapping("/show/case")
    public String showCase(@RequestParam String name, HttpServletRequest request, Model model) {
        //
        String viewName = name + "/" + name + "Show";
        //
        UIContext uiContext = new UIContext(viewName);
        uiContext.setObject(HttpServletRequest.class.getName(), request);
        //
        uiEngine.run(uiContext);
        //
        request.setAttribute(UIContext.class.getName(), uiContext);
        //
        return viewName;
    }

    @RequestMapping("/show/source")
    public String showSource(@RequestParam String name, @RequestParam String resource, Model model) throws IOException {
        //
        UIComponentResourceLoader componentResourceLoader = new ClassLoaderUIComponentResourceLoader();
        UIComponentResource componentResource = componentResourceLoader.load(name);
        //
        InputStream input = null;
        if(resource.equals(name + ".html")) {
            input = componentResource.getComponentUI();
        } else if(resource.equals(name + ".js")) {
            input = componentResource.getComponentScript();
        } else if(resource.equals(name + "Adapter.js")) {
            input = componentResource.getComponentAdapterScript();
        } else if(resource.endsWith(name + ".java")) {
            input = componentResource.getComponentClass();
        } else if(resource.endsWith(name + "Validate.js")) {
            input = componentResource.getComponentValidateScript(); 
        } else {
            throw new IllegalArgumentException("Unsupported resource " + resource);
        }
        //.
        String source = FileCopyUtils.copyToString(new InputStreamReader(input));
        model.addAttribute("source", source);
        model.addAttribute("resource", resource);
        //
        return themeName + "/layout_xdoc_source";
    }

}
