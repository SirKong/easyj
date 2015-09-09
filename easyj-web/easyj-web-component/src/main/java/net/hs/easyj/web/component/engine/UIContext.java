package net.hs.easyj.web.component.engine;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * UI 上下文
 *
 * @author Gavin Hu
 * @create 2015/5/6
 */
public final class UIContext {

    public static final String UI_COMPONENT_NAMES = "componentNames";

    private String viewName;

    private Map<String, Object> objects = new HashMap<>();

    public UIContext(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public <T> T getObject(String name) {
        return (T) objects.get(name);
    }

    public void setObject(String name, Object value) {
        this.objects.put(name, value);
    }

    public Set<String> getObjectNames() {
        return objects.keySet();
    }

}
