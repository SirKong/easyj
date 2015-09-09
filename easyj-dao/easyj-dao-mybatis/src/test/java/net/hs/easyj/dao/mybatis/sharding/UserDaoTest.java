package net.hs.easyj.dao.mybatis.sharding;

import net.hs.easyj.dao.mybatis.sharding.demo.User;
import net.hs.easyj.dao.mybatis.sharding.demo.UserDao;
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
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testAsc() {
        System.out.println(asc("_"));
    }

    private long asc(String str) {
        long l = 0;
        for(char c : str.toCharArray()) {
            l += c;
        }
        return l;
    }

    @Test
    public void testCreate() {
        User user = new User();
        user.setId(502L);
        user.setUsername("gavin");
        user.setPassword("123456");
        user.setAge(30);
        //
        userDao.create(user);
    }

    @Test
    public void testSelect() {
        Long id = 1L;
        //
        User user = userDao.retrieve(id);
    }

}
