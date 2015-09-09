package net.hs.easyj.cache.tair;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

import java.util.Collection;

/**
 * Tair 缓存管理器
 *
 * @author Gavin Hu
 * @create 2015/8/7
 */
public class TairCacheManager extends AbstractCacheManager {

    private Collection<TairCache> tairCaches;

    public void setTairCaches(Collection<TairCache> tairCaches) {
        this.tairCaches = tairCaches;
    }

    @Override
    protected Collection<? extends Cache> loadCaches() {
        return tairCaches;
    }

}
