package net.hs.easyj.config.provider.properties;

import net.hs.easyj.config.spi.ObjectDelegate;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 属性对象委托
 *
 * @author Gavin Hu
 * @create 2015/8/12
 */
public class PropertiesDelegate extends Properties implements ObjectDelegate<Properties> {
    
    private Properties target;

    public Properties getTarget() {
        return target;
    }

    @Override
    public void setTarget(Properties target) {
        this.target = target;
    }

    @Override
    public synchronized Object setProperty(String key, String value) {
        return getTarget().setProperty(key, value);
    }

    @Override
    public Enumeration<?> propertyNames() {
        return getTarget().propertyNames();
    }

    @Override
    public Set<String> stringPropertyNames() {
        return getTarget().stringPropertyNames();
    }

    @Override
    public String getProperty(String key) {
        return getTarget().getProperty(key);
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        return getTarget().getProperty(key, defaultValue);
    }

    @Override
    public boolean containsValue(Object value) {
        return getTarget().containsValue(value);
    }

    @Override
    public synchronized boolean containsKey(Object key) {
        return getTarget().containsKey(key);
    }

    @Override
    public synchronized Enumeration<Object> elements() {
        return getTarget().elements();
    }

    @Override
    public synchronized boolean contains(Object value) {
        return getTarget().contains(value);
    }

    @Override
    public synchronized boolean isEmpty() {
        return getTarget().isEmpty();
    }

    @Override
    public synchronized Enumeration<Object> keys() {
        return getTarget().keys();
    }

    @Override
    public synchronized int size() {
        return getTarget().size();
    }

    @Override
    public synchronized Object get(Object key) {
        return getTarget().get(key);
    }

    @Override
    public synchronized Object put(Object key, Object value) {
        return getTarget().put(key, value);
    }

    @Override
    public synchronized Object remove(Object key) {
        return getTarget().remove(key);
    }

    @Override
    public synchronized void putAll(Map<?, ?> t) {
        getTarget().putAll(t);
    }

    @Override
    public synchronized void clear() {
        getTarget().clear();
    }

    @Override
    public synchronized Object clone() {
        return getTarget().clone();
    }

    @Override
    public Set<Object> keySet() {
        return getTarget().keySet();
    }

    @Override
    public Set<Map.Entry<Object, Object>> entrySet() {
        return getTarget().entrySet();
    }

    @Override
    public synchronized boolean equals(Object o) {
        return getTarget().equals(o);
    }

    @Override
    public Collection<Object> values() {
        return getTarget().values();
    }

    @Override
    public synchronized int hashCode() {
        return getTarget().hashCode();
    }

    @Override
    public synchronized String toString() {
        return getTarget().toString();
    }

}
