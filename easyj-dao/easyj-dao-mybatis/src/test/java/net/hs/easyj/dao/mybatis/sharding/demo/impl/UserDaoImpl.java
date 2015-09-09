package net.hs.easyj.dao.mybatis.sharding.demo.impl;

import net.hs.easyj.dao.mybatis.sharding.demo.User;
import net.hs.easyj.dao.mybatis.sharding.demo.UserDao;
import net.hs.easyj.dao.mybatis.spring.support.MyBatisDaoSupport;
import net.hs.easyj.model.Page;
import org.springframework.stereotype.Repository;

/**
 * @author gavin
 * @create 15/6/21
 */
@Repository
public class UserDaoImpl extends MyBatisDaoSupport<User> implements UserDao {

    @Override
    public Page<User> findPage(Page page, String name) {
        User user = new User();
        user.setUsername(name);
        //
        return super.findPageByExample(page, user);
    }
}
