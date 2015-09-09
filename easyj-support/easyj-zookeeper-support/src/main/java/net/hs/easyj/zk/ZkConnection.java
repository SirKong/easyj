package net.hs.easyj.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;

/**
 * Zookeeper 连接
 *
 * @author Gavin Hu
 * @create 2015/7/8
 */
public final class ZkConnection {

    private CuratorFramework client;

    private String zookeepers;

    private int sessionTimeout = 60000;

    private int connectionTimeout = 15000;

    private int baseSleepTime = 1000;

    private int maxSleepTime = 2000;

    private int maxRetries = 3;

    public String getZookeepers() {
        return zookeepers;
    }

    public void setZookeepers(String zookeepers) {
        this.zookeepers = zookeepers;
    }

    public int getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getBaseSleepTime() {
        return baseSleepTime;
    }

    public void setBaseSleepTime(int baseSleepTime) {
        this.baseSleepTime = baseSleepTime;
    }

    public int getMaxSleepTime() {
        return maxSleepTime;
    }

    public void setMaxSleepTime(int maxSleepTime) {
        this.maxSleepTime = maxSleepTime;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public void init() {
        RetryPolicy retryPolicy = new BoundedExponentialBackoffRetry(baseSleepTime, maxSleepTime, maxRetries);
        this.client = CuratorFrameworkFactory.newClient(zookeepers, sessionTimeout, connectionTimeout, retryPolicy);
        this.client.start();
    }

    public CuratorFramework getClient() {
        return client;
    }

    public void close() {
        if(client!=null) {
           client.close();
        }
    }

}