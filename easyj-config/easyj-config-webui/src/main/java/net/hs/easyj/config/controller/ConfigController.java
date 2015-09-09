package net.hs.easyj.config.controller;

import net.hs.easyj.config.bean.ConfigBean;
import net.hs.easyj.config.definition.ObjectConfigDefinition;
import net.hs.easyj.config.definition.ObjectConfigDefinitionCategorizer;
import net.hs.easyj.config.definition.ObjectConfigDefinitionScanner;
import net.hs.easyj.config.spi.ObjectConfig;
import net.hs.easyj.config.storage.ObjectConfigPath;
import net.hs.easyj.config.storage.ObjectConfigStorage;
import net.hs.easyj.config.wrapper.ObjectConfigWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Iterator;
import java.util.List;

/**
 * 配置控制器
 *
 * @author Gavin Hu
 * @create 2015/8/10
 */
@Controller
@RequestMapping("/config")
@SessionAttributes({"configBean", "objectConfig", "categorizer"})
public class ConfigController {

    @Autowired
    private ObjectConfigStorage objectConfigStorage;

    @Autowired
    private ObjectConfigDefinitionScanner objectConfigDefinitionScanner;

    @RequestMapping(value="/add")
    public String configAdd(@ModelAttribute("configBean") ConfigBean configBean, Model model) {
        //
        ObjectConfigPath objectConfigPath = objectConfigStorage.tree(configBean.getPath());
        if(objectConfigPath!=null) {
            model.addAttribute("objectConfigPath", objectConfigPath);
        }
        //
        return "config/add";
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String configAdd(@ModelAttribute("configBean") ConfigBean configBean,
                            @ModelAttribute("categorizer") ObjectConfigDefinitionCategorizer categorizer,
                            String category, String subCategory1, String path, Model model) {
        //
        String error = hasError(categorizer, category, subCategory1, path);
        if(error!=null) {
            //
            model.addAttribute("error", error);
            return "redirect:/config/add.html?error={error}";
        }
        //
        String targetPath = getTargetPath(configBean, category, subCategory1, path);
        //
        ObjectConfigDefinition objectConfigDefinition = categorizer.getObjectConfigDefinition(category, subCategory1);
        //
        ObjectConfig objectConfig = newObjectConfig(objectConfigDefinition);
        //
        objectConfigStorage.store(targetPath, objectConfig);
        //
        model.addAttribute("targetPath", targetPath);
        //
        return "redirect:/config/edit.html?targetPath={targetPath}";
    }

    private String hasError(ObjectConfigDefinitionCategorizer categorizer, String category, String subCategory1, String path) {
        if(StringUtils.isEmpty(category)) {
            return "categoryIsEmpty";
        }
        if(!categorizer.getSubCategories1(category).isEmpty() && StringUtils.isEmpty(subCategory1)) {
            return "subCategory1IsEmpty";
        }
        if(StringUtils.isEmpty(path)) {
            return "pathIsEmpty";
        }
        //
        return null;
    }

    private ObjectConfig newObjectConfig(ObjectConfigDefinition objectConfigDefinition) {
        try {
            Class<?> objectConfigClass = ClassUtils.forName(objectConfigDefinition.getClassName());
            return (ObjectConfig) BeanUtils.instantiate(objectConfigClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getTargetPath(ConfigBean configBean, String category, String subCategory1, String path) {
        //
        Assert.notNull(configBean);
        Assert.notNull(category);
        Assert.notNull(path);
        //
        StringBuilder targetPathBuilder = new StringBuilder(configBean.getPath());
        targetPathBuilder.append("/").append(category);
        if(subCategory1!=null) {
            targetPathBuilder.append("/").append(subCategory1);
        }
        targetPathBuilder.append(path);
        //
        return targetPathBuilder.toString();
    }

    @RequestMapping(value="/edit")
    public String configEdit(@ModelAttribute("configBean") ConfigBean configBean, String targetPath, Model model) {
        //
        List<ObjectConfigDefinition> objectConfigDefinitions = objectConfigDefinitionScanner.scan();
        ObjectConfigDefinitionCategorizer categorizer = new ObjectConfigDefinitionCategorizer(objectConfigDefinitions);
        model.addAttribute("categorizer", categorizer);
        //
        ObjectConfigPath objectConfigPath = objectConfigStorage.tree(configBean.getPath());
        if(objectConfigPath!=null) {
            model.addAttribute("objectConfigPath", objectConfigPath);
        }
        if(targetPath!=null) {
            model.addAttribute("targetPath", targetPath);
            //
            Class<? extends ObjectConfig> objectConfigClass = determineObjectConfigClass(categorizer, configBean, targetPath);
            ObjectConfig objectConfig = objectConfigStorage.load(targetPath, objectConfigClass);
            //
            if(objectConfig!=null) {
                ObjectConfigWrapper objectConfigWrapper = new ObjectConfigWrapper(objectConfig);
                //
                model.addAttribute("objectConfig", objectConfig);
                model.addAttribute("objectConfigWrapper", objectConfigWrapper);
            }
        }
        //
        return "config/edit";
    }

    private Class<? extends ObjectConfig> determineObjectConfigClass(ObjectConfigDefinitionCategorizer categorizer, ConfigBean configBean, String targetPath) {
        String categoryPath = targetPath.substring(configBean.getPath().length());
        String[] categoryNames = StringUtils.tokenizeToStringArray(categoryPath, "/");
        String category = categoryNames[0];
        String subCategory1 = null;
        String subCategory2 = null;
        String subCategory3 = null;
        //
        ObjectConfigDefinition objectConfigDefinition = null;
        if(categorizer.getSubCategories1(category).isEmpty()) {
            objectConfigDefinition = categorizer.getObjectConfigDefinition(category);
        } else {
            subCategory1 = categoryNames[1];
        }
        //
        if(subCategory1!=null) {
            if (categorizer.getSubCategories2(subCategory1).isEmpty()) {
                objectConfigDefinition = categorizer.getObjectConfigDefinition(category, subCategory1);
            } else {
                subCategory2 = categoryNames[2];
            }
        }
        //
        if(subCategory2!=null) {
            if (categorizer.getSubCategories3(subCategory2).isEmpty()) {
                objectConfigDefinition = categorizer.getObjectConfigDefinition(category, subCategory2);
            } else {
                subCategory3 = categoryNames[3];
            }
        }
        //
        if(objectConfigDefinition!=null) {
            try {
                return (Class<? extends ObjectConfig>) ClassUtils.forName(objectConfigDefinition.getClassName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    public String configEdit(@ModelAttribute ConfigBean configBean, @ModelAttribute ObjectConfig objectConfig,
                             String targetPath, String action, Model model, WebRequest webRequest) {
        //
        if("delete".equals(action)) {
            objectConfigStorage.remove(targetPath);
            //
            return "redirect:/config/edit.html";
        } else {
            ObjectConfigWrapper objectConfigWrapper = new ObjectConfigWrapper(objectConfig);
            //
            for (Iterator<String> iter = webRequest.getParameterNames(); iter.hasNext(); ) {
                String paramName = iter.next();
                String paramValue = webRequest.getParameter(paramName);
                //
                if (objectConfigWrapper.hasPropertyName(paramName)) {
                    objectConfigWrapper.setPropertyValue(paramName, paramValue);
                }
            }
            //
            objectConfigStorage.store(targetPath, objectConfig);
            model.addAttribute("targetPath", targetPath);
            //
            return "redirect:/config/edit.html?targetPath={targetPath}";
        }
    }


}
