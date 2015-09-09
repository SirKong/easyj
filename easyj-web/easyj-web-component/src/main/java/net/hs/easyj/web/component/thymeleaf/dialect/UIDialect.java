package net.hs.easyj.web.component.thymeleaf.dialect;

import net.hs.easyj.web.component.thymeleaf.processor.UIComponentAttrProcessor;
import net.hs.easyj.web.component.thymeleaf.processor.UIDefinitionAttrProcessor;
import net.hs.easyj.web.component.thymeleaf.processor.UIEventAttrProcessors;
import net.hs.easyj.web.component.thymeleaf.processor.UIEventsAttrProcessor;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * UI 方言
 *
 * @author Gavin Hu
 * @create 2015/5/7
 */
public class UIDialect extends AbstractDialect {

    public static final String PREFIX = "ui";

    @Override
    public String getPrefix() {
        return PREFIX;
    }

    @Override
    public Set<IProcessor> getProcessors() {
        return new HashSet<IProcessor>(Arrays.asList(
                new UIDefinitionAttrProcessor(),
                new UIComponentAttrProcessor(),
                new UIEventsAttrProcessor(),
                UIEventAttrProcessors.EVENT_ON_ATTR_PROCESSOR,
                UIEventAttrProcessors.EVENT_TO_ATTR_PROCESSOR,
                UIEventAttrProcessors.EVENT_ADAPTER_ATTR_PROCESSOR
        ));
    }
}