
package net.hs.easyj.web.component.thymeleaf.processor;

import com.alibaba.fastjson.JSONObject;
import net.hs.easyj.web.component.engine.UIContext;
import org.springframework.util.StringUtils;
import org.thymeleaf.Arguments;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.dom.Attribute;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.Node;
import org.thymeleaf.processor.attr.AbstractFragmentHandlingAttrProcessor;
import org.thymeleaf.standard.fragment.StandardFragment;
import org.thymeleaf.standard.fragment.StandardFragmentProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UI 组件属性处理器
 *
 * @author Gavin
 * @create 2015/5/10
 */
public class UIComponentAttrProcessor extends AbstractFragmentHandlingAttrProcessor {

    public static final int ATTR_PRECEDENCE = 2000;
    public static final String ATTR_NAME = "component";

    private static final String ATTR_COMPONENT_NAME = "component-name";
    private static final String ATTR_COMPONENT_META = "component-meta";
    private static final String ATTR_COMPONENT_DATA = "component-data";

    public UIComponentAttrProcessor() {
        super(ATTR_NAME);
    }

    @Override
    protected List<Node> computeFragment(Arguments arguments, Element element, String attributeName, String attributeValue) {
        //
        attributeValue = parseComponentValue(attributeName, attributeValue);
        //
        final String dialectPrefix = Attribute.getPrefixFromAttributeName(attributeName);

        final String fragmentSignatureAttributeName =
                getFragmentSignatureUnprefixedAttributeName(arguments, element, attributeName, attributeValue);

        final StandardFragment fragment =
                StandardFragmentProcessor.computeStandardFragmentSpec(
                        arguments.getConfiguration(), arguments, attributeValue, dialectPrefix, fragmentSignatureAttributeName);

        final StandardFragment modifiedFragment = processFragment(arguments, fragment);

        final List<Node> extractedNodes =
                modifiedFragment.extractFragment(arguments.getConfiguration(), arguments, arguments.getTemplateRepository());

        final boolean removeHostNode = getRemoveHostNode(arguments, element, attributeName, attributeValue);

        // If fragment is a whole document (no selection inside), we should never remove its parent node/s
        // Besides, we know that StandardFragmentProcessor.computeStandardFragmentSpec only creates two types of
        // IFragmentSpec objects: WholeFragmentSpec and DOMSelectorFragmentSpec.
        //final boolean isWholeDocument = (modifiedFragment.getFragmentSpec() instanceof WholeFragmentSpec);

        if (extractedNodes == null || removeHostNode) {
            return extractedNodes;
        }

        // Host node is NOT to be removed, therefore what should be removed is the top-level elements of the returned
        // nodes.

        final Element containerElement = new Element("container");

        for (final Node extractedNode : extractedNodes) {
            // This is done in this indirect way in order to preserver internal structures like e.g. local variables.
            containerElement.addChild(extractedNode);
            containerElement.extractChild(extractedNode);
        }

        final List<Node> extractedChildren = containerElement.getChildren();
        containerElement.clearChildren();
        // process element
        processElement(arguments, element, attributeName, attributeValue);
        return extractedChildren;
    }

    private void processElement(Arguments arguments, Element element, String attributeName, String attributeValue) {
        //
        element.setAttribute(ATTR_COMPONENT_NAME, attributeValue);
        //
        IWebContext webContext = (IWebContext) arguments.getContext();
        UIContext uiContext = (UIContext) webContext.getHttpServletRequest().getAttribute(UIContext.class.getName());
        //
        String metaKey = StringUtils.uncapitalize(attributeValue) + "Meta";
        Object metaValue = uiContext.getObject(metaKey);
        String metaJson = JSONObject.toJSONString(metaValue);
        element.setAttribute(ATTR_COMPONENT_META, metaJson);
        webContext.getVariables().put(metaKey, metaValue);
        //
        String dataKey = StringUtils.uncapitalize(attributeValue) + "Data";
        Object dataValue = uiContext.getObject(dataKey);
        String dataJson = JSONObject.toJSONString(dataValue);
        element.setAttribute(ATTR_COMPONENT_DATA, dataJson);
        webContext.getVariables().put(dataKey, dataValue);
    }

    private StandardFragment processFragment(Arguments arguments, StandardFragment fragment) {
        //
        Map<String, Object> fragmentParameters = new HashMap<String, Object>();
        //
        String templateName = fragment.getTemplateName() + "/" + fragment.getTemplateName();
        //
        return new StandardFragment(templateName, fragment.getFragmentSpec(), fragmentParameters);
    }

    private String parseComponentValue(String attributeName, String attributeValue) {
        return attributeValue;
    }

    @Override
    protected boolean getRemoveHostNode(Arguments arguments, Element element, String attributeName, String attributeValue) {
        return false;
    }

    @Override
    public int getPrecedence() {
        return ATTR_PRECEDENCE;
    }

    protected String getFragmentSignatureUnprefixedAttributeName(Arguments arguments, Element element, String attributeName, String attributeValue) {
        return "definition";
    }

}