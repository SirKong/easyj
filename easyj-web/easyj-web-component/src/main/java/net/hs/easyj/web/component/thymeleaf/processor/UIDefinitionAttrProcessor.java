package net.hs.easyj.web.component.thymeleaf.processor;

import org.thymeleaf.processor.attr.AbstractNoOpAttrProcessor;

/**
 * UI 定义属性处理器
 *
 * @author Gavin Hu
 * @create 2015/5/8
 */
public class UIDefinitionAttrProcessor extends AbstractNoOpAttrProcessor {

    public static final int ATTR_PRECEDENCE = 1500;
    public static final String ATTR_NAME = "definition";

    public UIDefinitionAttrProcessor() {
        super(ATTR_NAME);
    }

    @Override
    public int getPrecedence() {
        return ATTR_PRECEDENCE;
    }

}
