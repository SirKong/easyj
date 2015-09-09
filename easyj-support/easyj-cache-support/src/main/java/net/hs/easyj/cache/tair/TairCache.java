package net.hs.easyj.cache.tair;

import com.taobao.tair.DataEntry;
import com.taobao.tair.Result;
import com.taobao.tair.ResultCode;
import com.taobao.tair.TairManager;
import net.hs.easyj.cache.KeyWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * 基于淘宝 Tair 的缓存实现
 *
 * @author Gavin Hu
 * @create 2015/8/7
 */
public class TairCache implements Cache, InitializingBean {

    private static final Log log = LogFactory.getLog(TairCache.class);

    private String name;

    private int namespace;

    private TairManager tairManager;

    public void setName(String name) {
        this.name = name;
    }

    public void setNamespace(int namespace) {
        this.namespace = namespace;
    }

    public void setTairManager(TairManager tairManager) {
        this.tairManager = tairManager;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(name);
        Assert.isTrue(namespace>=0 && namespace<=1023);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return this.tairManager;
    }

    @Override
    public ValueWrapper get(Object key) {
        Result<DataEntry> result = this.tairManager.get(namespace, (Serializable)key);
        if(result.isSuccess()) {
            DataEntry dataEntry = result.getValue();
            if(dataEntry!=null) {
                return net.hs.easyj.cache.ValueWrapper.wrap(dataEntry.getValue());
            }
        }
        return net.hs.easyj.cache.ValueWrapper.wrap(null);
    }

    @Override
    public void put(Object key, Object value) {
        ResultCode resultCode = ResultCode.UNKNOW;
        if(key instanceof KeyWrapper) {
            KeyWrapper keyWrapper = (KeyWrapper) key;
            resultCode = this.tairManager.put(namespace,
                                            (Serializable)keyWrapper.getKey(),
                                            (Serializable)value,
                                            keyWrapper.getVersion(),
                                            keyWrapper.getExpireTime());
        } else {
            resultCode = this.tairManager.put(namespace, (Serializable)key, (Serializable)value);
        }
        if(!resultCode.isSuccess()) {
            log.error(resultCode.toString());
        }
    }

    @Override
    public void evict(Object key) {
        ResultCode resultCode = this.tairManager.delete(namespace, (Serializable)key);
        if(!resultCode.isSuccess()) {
            log.error(resultCode.toString());
        }
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

}
