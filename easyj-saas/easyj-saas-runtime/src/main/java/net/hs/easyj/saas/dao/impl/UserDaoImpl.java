package net.hs.easyj.saas.dao.impl;

import net.hs.easyj.dao.mybatis.spring.support.MyBatisDaoSupport;
import net.hs.easyj.saas.dao.UserDao;
import net.hs.easyj.saas.model.User;
import org.springframework.stereotype.Repository;

/**
 * 用户 Dao 实现
 *
 * @author Gavin Hu
 * @create 2015/8/26
 */
@Repository
public class UserDaoImpl extends MyBatisDaoSupport<User> implements UserDao {
}
