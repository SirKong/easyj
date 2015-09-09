package net.hs.easyj.web.component.thymeleaf.processor;

import org.thymeleaf.processor.attr.AbstractNoOpAttrProcessor;

/**
 * UI 事件 属性处理器
 *
 * @author Gavin Hu
 * @create 2015/5/11
 */
public class UIEventAttrProcessors {

    public static final String ATTR_EVENT_ON = "event-on";
    public static final String ATTR_EVENT_TO = "event-to";
    public static final String ATTR_EVENT_ADAPTER = "event-adapter";

    public static final UIEventOnAttrProcessor EVENT_ON_ATTR_PROCESSOR = new UIEventOnAttrProcessor();
    public static final UIEventToAttrProcessor EVENT_TO_ATTR_PROCESSOR = new UIEventToAttrProcessor();
    public static final UIEventAdapterAttrProcessor EVENT_ADAPTER_ATTR_PROCESSOR = new UIEventAdapterAttrProcessor();

    private static class UIEventOnAttrProcessor extends AbstractNoOpAttrProcessor {

        protected UIEventOnAttrProcessor() {
            super(ATTR_EVENT_ON);
        }

        @Override
        public int getPrecedence() {
            return 1000;
        }
    }

    private static class UIEventToAttrProcessor extends AbstractNoOpAttrProcessor {

        protected UIEventToAttrProcessor() {
            super(ATTR_EVENT_TO);
        }

        @Override
        public int getPrecedence() {
            return 1000;
        }
    }

    private static class UIEventAdapterAttrProcessor extends AbstractNoOpAttrProcessor {

        protected UIEventAdapterAttrProcessor() {
            super(ATTR_EVENT_ADAPTER);
        }

        @Override
        public int getPrecedence() {
            return 1000;
        }
    }
}
