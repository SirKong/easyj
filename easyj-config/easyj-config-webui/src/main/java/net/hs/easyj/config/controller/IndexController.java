package net.hs.easyj.config.controller;

import net.hs.easyj.config.bean.ConfigBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author Gavin Hu
 * @create 2015/8/10
 */
@Controller
@SessionAttributes("configBean")
public class IndexController {

    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String loginView() {
        //
        return "index";
    }

    @RequestMapping(value="/index", method = RequestMethod.POST)
    public String loginAction(ConfigBean configBean, Model model) {
        //
        model.addAttribute("configBean", configBean);
        //
        return "redirect:config/edit.html";
    }

}
