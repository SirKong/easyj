package net.hs.easyj.web.widget.view.thymeleaf.dialect;

import net.hs.easyj.web.widget.view.thymeleaf.processor.layout.LayoutPositionAttrProcessor;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 布局方言
 *
 * @author Gavin Hu
 * @create 2015/2/26
 */
public class LayoutDialect extends AbstractDialect {

    public static final String PREFIX = "layout";

    @Override
    public String getPrefix() {
        return PREFIX;
    }

    @Override
    public Set<IProcessor> getProcessors() {
        Set<IProcessor> processors = new LinkedHashSet<IProcessor>();
        processors.add(new LayoutPositionAttrProcessor());
        //
        return processors;
    }

}
