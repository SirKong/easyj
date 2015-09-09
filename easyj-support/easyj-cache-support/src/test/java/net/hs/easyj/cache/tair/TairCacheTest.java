package net.hs.easyj.cache.tair;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * TairCache 单元测试
 *
 * @author Gavin Hu
 * @create 2015/8/7
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:cacheContext.xml")
public class TairCacheTest {

    private Cache cache;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setup() {
        //
        this.cache = this.cacheManager.getCache("defaultCache");
    }

    @Test
    public void testPut() {
        //
        this.cache.put("company", "hundsun");
        //
    }

    @Test
    public void testGet() {
        //
        Object value = this.cache.get("name").get();
        System.out.println(value);
    }

}
