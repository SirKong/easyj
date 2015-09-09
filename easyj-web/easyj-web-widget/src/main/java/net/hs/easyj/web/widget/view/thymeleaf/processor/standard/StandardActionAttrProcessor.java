package net.hs.easyj.web.widget.view.thymeleaf.processor.standard;

import net.hs.easyj.web.widget.entry.config.WidgetConfig;
import org.thymeleaf.Arguments;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.ProcessorResult;
import org.thymeleaf.processor.attr.AbstractAttrProcessor;

/**
 * 包装标准的 action 属性处理器
 *
 * @author Gavin Hu
 * @create 15/7/7
 */
public class StandardActionAttrProcessor extends AbstractAttrProcessor {

    public StandardActionAttrProcessor() {
        super(org.thymeleaf.standard.processor.attr.StandardActionAttrProcessor.ATTR_NAME);
    }

    @Override
    protected ProcessorResult processAttribute(Arguments arguments, Element element, String attributeName) {
        //
        IWebContext webContext = (IWebContext) arguments.getContext();
        WidgetConfig widgetConfig = (WidgetConfig) webContext.getHttpServletRequest()
                .getAttribute(WidgetConfig.class.getName());
        if(widgetConfig !=null) {
            Element input = new Element("input");
            input.setAttribute("type", "hidden");
            input.setAttribute("name", "widgetId");
            input.setAttribute("value", widgetConfig.getId());
            //
            element.insertChild(0, input);
        }
        //
        return ProcessorResult.OK;
    }

    @Override
    public int getPrecedence() {
        return org.thymeleaf.standard.processor.attr.StandardActionAttrProcessor.ATTR_PRECEDENCE - 1;
    }
}
