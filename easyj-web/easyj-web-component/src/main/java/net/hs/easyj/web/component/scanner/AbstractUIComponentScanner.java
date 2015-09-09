package net.hs.easyj.web.component.scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 抽象的 UI 组件扫描器
 *
 * @author Gavin Hu
 * @create 2015/5/7
 */
public abstract class AbstractUIComponentScanner implements UIComponentScanner {

    private static Logger logger = LoggerFactory.getLogger(AbstractUIComponentScanner.class);

    private boolean cache = true;

    private Map<String, Set<String>> componentNamesCache = new HashMap<>();

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    @Override
    public Set<String> scan(String viewName) {
        //
        try {
            if (cache && componentNamesCache.containsKey(viewName)) {
                return componentNamesCache.get(viewName);
            }
            //
            Set<String> componentNames = doScan(viewName);
            componentNamesCache.put(viewName, componentNames);
            //
            return componentNames;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Collections.EMPTY_SET;
        }
    }

    protected abstract Set<String> doScan(String viewName) throws Exception;

}
