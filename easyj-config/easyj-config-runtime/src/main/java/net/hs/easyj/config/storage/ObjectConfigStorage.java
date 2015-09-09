package net.hs.easyj.config.storage;

import net.hs.easyj.config.spi.ObjectConfig;

/**
 * 对象配置存储
 *
 * @author Gavin Hu
 * @create 2015/8/10
 */
public interface ObjectConfigStorage {

    /**
     * 配置树
     * @param path
     * @return
     */
    ObjectConfigPath tree(String path);

    /**
     * 配置移除
     * @param path
     */
    void remove(String path);

    /**
     * 配置存放
     * @param path
     * @param objectConfig
     * @return
     */
    void store(String path, ObjectConfig objectConfig);

    /**
     * 配置加载
     * @param path
     * @return
     */
    <T extends ObjectConfig> T load(String path, Class<T> objectConfigType);
}
