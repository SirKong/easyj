package net.hs.easyj.web.component.resource;

import java.util.HashMap;
import java.util.Map;

/**
 * 抽象的 UI 资源加载器
 *
 * @author Gavin Hu
 * @create 2015/5/15
 */
public abstract class AbstractUIComponentResourceLoader implements UIComponentResourceLoader {

    private boolean cache;

    private Map<String, UIComponentResource> resourceCache = new HashMap<>();

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    @Override
    public UIComponentResource load(String name) {
        //
        UIComponentResource resource = resourceCache.get(name);
        if(cache && resourceCache.containsKey(name)) {
            return resource;
        }
        //
        if(resource==null) {
            synchronized (this) {
                if(resource==null) {
                    resource = doLoad(name);
                    this.resourceCache.put(name, resource);
                }
            }
        }
        //
        return resource;
    }

    protected abstract UIComponentResource doLoad(String name);

}
