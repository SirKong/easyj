package net.hs.easyj.config.storage;

import com.alibaba.fastjson.JSONObject;
import net.hs.easyj.config.spi.ObjectConfig;
import net.hs.easyj.zk.ZkConnection;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * 基于 Zookeeper 的对象配置存储
 *
 * @author Gavin Hu
 * @create 2015/8/10
 */
public class ZkObjectConfigStorage implements ObjectConfigStorage {

    private ZkConnection zkConnection;

    public void setZkConnection(ZkConnection zkConnection) {
        this.zkConnection = zkConnection;
    }

    @Override
    public ObjectConfigPath tree(String zkPath) {
        //
        try {
            Stat stat = zkConnection.getClient().checkExists().forPath(zkPath);
            if(stat==null) {
                return null;
            }
            //
            ObjectConfigPath rootPath = new ObjectConfigPath(zkPath);
            //
            walkTree(rootPath);
            //
            return rootPath;
            //
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void remove(String zkPath) {
        //
        try {
            Stat stat = zkConnection.getClient().checkExists().forPath(zkPath);
            if(stat!=null) {
                zkConnection.getClient().delete().forPath(zkPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void walkTree(ObjectConfigPath configPath) {
        //
        try {
            String zkPath = configPath.getPath();
            List<String> children = zkConnection.getClient().getChildren().forPath(zkPath);
            for (String child : children) {
                ObjectConfigPath childConfigPath = new ObjectConfigPath(zkPath + "/" + child);
                childConfigPath.setParent(configPath);
                configPath.getChildren().add(childConfigPath);
                //
                walkTree(childConfigPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void store(String zkPath, ObjectConfig objectConfig) {
        try {
            byte[] zkData = JSONObject.toJSONBytes(objectConfig);
            Stat stat = zkConnection.getClient().checkExists().forPath(zkPath);
            if(stat==null) {
                zkConnection.getClient().create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(zkPath, zkData);
            } else {
                zkConnection.getClient().setData()
                        .forPath(zkPath, zkData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T extends ObjectConfig> T load(String path, Class<T> objectConfigType) {
        try {
            byte[] zkData = zkConnection.getClient().getData().forPath(path.toString());
            return JSONObject.parseObject(zkData, objectConfigType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
