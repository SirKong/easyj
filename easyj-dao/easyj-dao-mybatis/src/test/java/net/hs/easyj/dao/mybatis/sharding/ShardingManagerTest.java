package net.hs.easyj.dao.mybatis.sharding;

import net.hs.easyj.dao.mybatis.sharding.model.FragmentTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author gavin
 * @create 15/6/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
public class ShardingManagerTest {

    @Autowired
    private ShardingManager shardingManager;

    @Test
    public void testLookup() {
        //
        FragmentTable fragmentTable = shardingManager.lookup("tb_user", 1L);
    }


}
