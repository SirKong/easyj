package net.hs.easyj.web.widget.entry.loader;

import net.hs.easyj.web.widget.entry.config.PageConfig;
import net.hs.easyj.web.widget.entry.config.PositionConfig;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 抽象的页面配置加载器
 *
 * @author Gavin Hu
 * @create 2015/2/26
 */
public abstract class AbstractPageLoader implements PageLoader {

    private String prefix;

    private String suffix;

    private boolean cache = true;

    private Map<String, PageConfig> pageConfigCache  = new HashMap<>();

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    @Override
    public PageConfig load(String path, Map<String, String> params) {
        if(cache && pageConfigCache.containsKey(path)) {
            return pageConfigCache.get(path);
        }
        //
        PageConfig pageConfig = doLoad(path, params);
        pageConfigCache.put(path, pageConfig);
        //
        return pageConfig;
    }

    protected abstract PageConfig doLoad(String path, Map<String, String> params);

    protected void mergePageConfigFromParent(PageConfig pageConfig, PageConfig parentPageConfig) {
        // merge title
        if(pageConfig.getTitle()==null) {
            pageConfig.setTitle(parentPageConfig.getTitle());
        }
        // merge layout
        if(pageConfig.getLayout()==null) {
            pageConfig.setLayout(parentPageConfig.getLayout());
        }
        // merge resources
        if(pageConfig.getResources()==null) {
            pageConfig.setResources(parentPageConfig.getResources());
        }
        // merge position
        Set<String> positionNames = new HashSet<String>();
        for(PositionConfig positionConfig : pageConfig.getPositionConfigs()) {
            positionNames.add(positionConfig.getName());
        }
        for(PositionConfig parentPositionConfig : parentPageConfig.getPositionConfigs()) {
            if(positionNames.contains(parentPositionConfig.getName())==false) {
                pageConfig.getPositionConfigs().add(parentPositionConfig);
            }
        }
    }

    protected String getPageLocation(String name) {
        if(name.startsWith("/")) {
            return prefix + name + suffix;
        }
        return prefix + "/" + name + suffix;
    }

}
