package net.hs.easyj.config.storage;

import net.hs.easyj.zk.ZkConnection;
import org.junit.Before;
import org.junit.Test;

/**
 * @author gavin
 * @create 15/8/10
 */
public class ObjectConfigStorageTest {

    private ZkObjectConfigStorage objectConfigStorage;

    @Before
    public void setup() {
        //
        ZkConnection zkConnection = new ZkConnection();
        zkConnection.setZookeepers("localhost:2181");
        zkConnection.init();
        //
        this.objectConfigStorage = new ZkObjectConfigStorage();
        this.objectConfigStorage.setZkConnection(zkConnection);
    }

    @Test
    public void testTree() {
        ObjectConfigPath path = this.objectConfigStorage.tree("/config");
        //
        System.out.println(path);
    }

}
