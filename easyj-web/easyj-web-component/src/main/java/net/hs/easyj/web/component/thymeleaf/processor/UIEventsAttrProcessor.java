package net.hs.easyj.web.component.thymeleaf.processor;

import com.alibaba.fastjson.JSONObject;
import net.hs.easyj.web.component.thymeleaf.dialect.UIDialect;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.ProcessorResult;
import org.thymeleaf.processor.attr.AbstractAttrProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UI 事件集 属性处理器
 *
 * @author Gavin Hu
 * @create 2015/5/11
 */
public class UIEventsAttrProcessor extends AbstractAttrProcessor {

    public static final String ATTR_NAME = "events";
    public static final int ATTR_PRECEDENCE = 2000;

    private static final String ATTR_COMPONENT_EVENT = "component-event";

    public UIEventsAttrProcessor() {
        super(ATTR_NAME);
    }

    @Override
    protected ProcessorResult processAttribute(Arguments arguments, Element element, String attributeName) {
        //
        List<Element> children = element.getElementChildren();
        for(Element child : children) {
            //
            Map<String, String> eventDefinition = new HashMap<>();
            //
            String eventOnAttributeName = UIDialect.PREFIX + ":" + UIEventAttrProcessors.ATTR_EVENT_ON;
            String eventOnAttributeValue = child.getAttributeValue(eventOnAttributeName);
            Assert.hasText(eventOnAttributeName, "Attribute Name " + eventOnAttributeValue + "can not be null!");
            //
            String eventToAttributeName = UIDialect.PREFIX + ":" + UIEventAttrProcessors.ATTR_EVENT_TO;
            String eventToAttributeValue = child.getAttributeValue(eventToAttributeName);
            Assert.hasText(eventOnAttributeName, "Attribute Name " + eventToAttributeValue + "can not be null!");
            //
            String eventAdapterAttributeName = UIDialect.PREFIX + ":" + UIEventAttrProcessors.ATTR_EVENT_ADAPTER;
            String eventAdapterAttributeValue = child.getAttributeValue(eventAdapterAttributeName);
            //
            String[] sourceParts = StringUtils.split(eventOnAttributeValue, ":");
            eventDefinition.put("source", sourceParts[0]);
            eventDefinition.put("sourceEvent", sourceParts[1]);
            //
            String[] targetParts = StringUtils.split(eventToAttributeValue, ":");
            eventDefinition.put("target", targetParts[0]);
            eventDefinition.put("targetEvent", targetParts[1]);
            //
            if(StringUtils.hasText(eventAdapterAttributeValue)) {
                eventDefinition.put("eventAdapter", eventAdapterAttributeValue);
            }
            //
            child.setAttribute(ATTR_COMPONENT_EVENT, JSONObject.toJSONString(eventDefinition));
        }
        //
        element.removeAttribute(attributeName);
        element.setAttribute("style", "display:none");
        //
        return ProcessorResult.OK;
    }

    @Override
    public int getPrecedence() {
        return ATTR_PRECEDENCE;
    }
}
