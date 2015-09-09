package net.hs.easyj.config.storage;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 对象配置路径
 *
 * @author Gavin Hu
 * @create 2015/8/10
 */
public class ObjectConfigPath {

    private String path;

    private ObjectConfigPath parent;

    private Set<ObjectConfigPath> children = new LinkedHashSet<>();

    public ObjectConfigPath(String path) {
        this.path = path;
    }

    public String getName() {
        return path.substring(path.lastIndexOf("/"));
    }

    public String getPath() {
        return path;
    }

    public ObjectConfigPath getParent() {
        return parent;
    }

    public void setParent(ObjectConfigPath parent) {
        this.parent = parent;
    }

    public Set<ObjectConfigPath> getChildren() {
        return children;
    }

}
