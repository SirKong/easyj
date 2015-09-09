package net.hs.easyj.web.widget.view.thymeleaf.processor.layout;

import net.hs.easyj.web.extender.fork.ForkableDispatcher;
import net.hs.easyj.web.extender.fork.ForkedRequestAndResponse;
import net.hs.easyj.web.widget.entry.config.PageConfig;
import net.hs.easyj.web.widget.entry.config.WidgetConfig;
import net.hs.easyj.web.widget.entry.config.PositionConfig;
import net.hs.easyj.web.widget.entry.utils.PageUtils;
import org.thymeleaf.Arguments;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.NestableNode;
import org.thymeleaf.dom.Node;
import org.thymeleaf.dom.Text;
import org.thymeleaf.processor.ProcessorResult;
import org.thymeleaf.processor.attr.AbstractAttrProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * position 属性处理器
 *
 * @author Gavin Hu
 * @create 2015/2/26
 */
public class LayoutPositionAttrProcessor extends AbstractAttrProcessor {

    private static final String ATTR_NAME = "position";

    public LayoutPositionAttrProcessor() {
        super(ATTR_NAME);
    }

    @Override
    protected ProcessorResult processAttribute(Arguments arguments, Element element, String attributeName) {
        //
        String positionName = element.getAttributeValue(attributeName);
        //
        IWebContext webContext = (IWebContext) arguments.getContext();
        PageConfig pageConfig = PageUtils.getPageConfig(webContext.getHttpServletRequest());
        Map<String, ForkedRequestAndResponse> forkedMap = (Map<String, ForkedRequestAndResponse>)
                webContext.getHttpServletRequest().getAttribute(ForkableDispatcher.FORKED_REQUEST_AND_RESPONSES);
        //
        List<Node> children = new ArrayList<Node>();
        for(PositionConfig positionConfig : pageConfig.getPositionConfigs()) {
            if(!positionName.equals(positionConfig.getName())) {
                continue;
            }
            //
            for(WidgetConfig widgetConfig : positionConfig.getWidgetConfigs()) {
                ForkedRequestAndResponse forkedRequestAndResponse = forkedMap.get(widgetConfig.getId());
                String html = forkedRequestAndResponse.getForkedResponse().toString();
                Text pageletHtml = new Text(html , null, null, true);
                children.add(pageletHtml);
            }
        }
        //
        NestableNode parent = element.getParent();
        parent.removeChild(element);
        for(Node child : children) {
            parent.addChild(child);
        }
        //
        return ProcessorResult.OK;
    }

    @Override
    public int getPrecedence() {
        return 0;
    }
}
