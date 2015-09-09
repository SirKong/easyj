package net.hs.easyj.cache;

/**
 * Key 包装器
 *
 * @author Gavin Hu
 * @create 2015/8/7
 */
public class KeyWrapper {

    private Object key;

    private int version = 0;

    private int expireTime = 0;

    private KeyWrapper(Object key, int version, int expireTime) {
        this.key = key;
        this.version = version;
        this.expireTime = expireTime;
    }

    public Object getKey() {
        return key;
    }

    public int getVersion() {
        return version;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public static KeyWrapper wrap(String key, int version) {
        return new KeyWrapper(key, version, 0);
    }

    public static KeyWrapper wrap(String key, int version, int expireTime) {
        return new KeyWrapper(key, version, expireTime);
    }

}
