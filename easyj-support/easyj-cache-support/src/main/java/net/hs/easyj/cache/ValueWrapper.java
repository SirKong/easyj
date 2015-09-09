package net.hs.easyj.cache;

import org.springframework.cache.Cache;

/**
 * Value 包装器
 *
 * @author Gavin Hu
 * @create 2015/8/7
 */
public class ValueWrapper implements Cache.ValueWrapper {

    private Object value;

    private ValueWrapper(Object value) {
        this.value = value;
    }

    @Override
    public Object get() {
        return value;
    }

    public static ValueWrapper wrap(Object value) {
        return new ValueWrapper(value);
    }

}
