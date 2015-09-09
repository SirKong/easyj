package net.hs.easyj.web.component.support;

import net.hs.easyj.web.component.UIComponent;
import org.springframework.util.StringUtils;

/**
 * UI 组件支持类
 *
 * @author Gavin Hu
 * @create 2015/5/8
 */
public abstract class UIComponentSupport implements UIComponent {

    private Object loadedMeta;

    private Object loadedData;

    private boolean cacheMeta = true;

    private boolean cacheData = true;

    @Override
    public boolean isAsync() {
        return false;
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getMetaKey() {
        return StringUtils.uncapitalize(getName()) + "Meta";
    }

    @Override
    public String getDataKey() {
        return StringUtils.uncapitalize(getName()) + "Data";
    }

    @Override
    public Object getMeta() {
        if(!cacheMeta || loadedMeta==null) {
            synchronized (this) {
                if(!cacheMeta || loadedMeta==null) {
                    this.loadedMeta = loadMeta();
                }
            }
        }
        return loadedMeta;
    }

    @Override
    public Object getData() {
        if(!cacheData || loadedData==null) {
            synchronized (this) {
                if(!cacheData || loadedData==null) {
                    this.loadedData = loadData();
                }
            }
        }
        return loadedData;
    }

    public void setCacheMeta(boolean cacheMeta) {
        this.cacheMeta = cacheMeta;
    }

    public void setCacheData(boolean cacheData) {
        this.cacheData = cacheData;
    }

    protected Object loadMeta() {
        return new Object();
    }

    protected Object loadData() {
        return new Object();
    }

}
