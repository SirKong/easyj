package net.hs.easyj.web.platform.theme;

import net.hs.easyj.web.resource.WebResourceFactory;
import net.hs.easyj.web.resource.WebResources;
import net.hs.easyj.web.resource.xml.JaxbWebResourceFactory;
import net.hs.easyj.web.widget.entry.config.PageConfig;
import net.hs.easyj.web.widget.entry.utils.PageUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 主题控制器
 *
 * @author Gavin Hu
 * @create 2015/8/22
 */
@Controller
public class ThemeController {

    private String theme = "default";

    private String skin = "default";

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    private WebResourceFactory webResourceFactory = new JaxbWebResourceFactory();

    @RequestMapping("/**/*.html")
    public String render(PageConfig pageConfig, Model model) throws Exception {
        //
        model.addAttribute("theme", theme);
        model.addAttribute("skin", skin);
        //
        WebResources webResources  = new WebResources(webResourceFactory);
        webResources.addWebResources(pageConfig.getResourceNames());
        //
        model.addAttribute("webResources", webResources);
        //
        return theme + "/" + pageConfig.getLayout();
    }

}
