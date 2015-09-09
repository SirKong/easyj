package net.hs.easyj.config.spring;

import com.alibaba.fastjson.JSONObject;
import net.hs.easyj.config.spi.ObjectConfig;
import net.hs.easyj.config.spi.ObjectCreator;
import net.hs.easyj.config.spi.ObjectDelegate;
import net.hs.easyj.config.spi.ObjectListener;
import net.hs.easyj.zk.ZkConnection;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author gavin
 * @create 15/8/12
 */
public abstract class ObjectConfigFactoryBean<T> implements FactoryBean<T>,
        PathChildrenCacheListener, InitializingBean, DisposableBean {

    private String system;

    private String profile;

    private String category;

    private String configPath;

    private Class objectType;

    private Class objectConfigClass;

    private ObjectCreator objectCreator;

    private ObjectDelegate objectDelegate;

    private ObjectListener objectListener;

    private ZkConnection zkConnection;

    private PathChildrenCache pathChildrenCache;

    private CountDownLatch latch = new CountDownLatch(1);

    public void setSystem(String system) {
        this.system = system;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public void setObjectType(Class objectType) {
        this.objectType = objectType;
    }

    public void setObjectConfigClass(Class objectConfigClass) {
        this.objectConfigClass = objectConfigClass;
    }

    public void setObjectCreator(ObjectCreator objectCreator) {
        this.objectCreator = objectCreator;
    }

    public void setObjectDelegate(ObjectDelegate objectDelegate) {
        this.objectDelegate = objectDelegate;
    }

    public void setObjectListener(ObjectListener objectListener) {
        this.objectListener = objectListener;
    }

    public void setZkConnection(ZkConnection zkConnection) {
        this.zkConnection = zkConnection;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(system, "Property system can not be null!");
        Assert.hasText(profile, "Property profile can not be null!");
        Assert.hasText(category, "Property category can not be null!");
        Assert.hasText(configPath, "Property configPath can not be null!");
        //
        Assert.notNull(zkConnection, "Property zkConnection can not be null!");
        //
        Assert.notNull(objectType, "Property objectType can not be null!");
        Assert.notNull(objectConfigClass, "Property objectConfigClass can not be null");
        Assert.notNull(objectCreator, "Property objectCreator can not be null!");
        Assert.notNull(objectDelegate, "Property objectDelegate can not be null!");
        //
        this.pathChildrenCache = new PathChildrenCache(zkConnection.getClient(), getBasePath(), true);
        this.pathChildrenCache.getListenable().addListener(this);
        this.pathChildrenCache.start();
        //
        latch.await(3, TimeUnit.SECONDS);
    }

    private String getBasePath() {
        return String.format("/%s/%s/%s", system, profile, category);
    }

    private String getTargetPath() {
        return getBasePath() + configPath;
    }

    @Override
    public void destroy() throws Exception {
        this.pathChildrenCache.close();
    }

    @Override
    public final void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
        //
        String zkPath = event.getData().getPath();
        if (zkPath.equals(getTargetPath())) {
            //
            byte[] zkData = event.getData().getData();
            ObjectConfig objectConfig = JSONObject.parseObject(zkData, objectConfigClass);
            //
            if(objectConfig.isEnabled()) {
                Object object = this.objectCreator.create(objectConfig);
                this.objectDelegate.setTarget(object);
                // fire change
                if(this.objectListener!=null) {
                    this.objectListener.change(objectDelegate);
                }
            }
            //
            latch.countDown();
        }
    }

    @Override
    public final T getObject() throws Exception {
        return (T) objectDelegate;
    }

    @Override
    public final Class getObjectType() {
        return objectType;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}


