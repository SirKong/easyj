package net.hs.easyj.saas.dao.impl;

import net.hs.easyj.dao.mybatis.spring.support.MyBatisDaoSupport;
import net.hs.easyj.saas.dao.UserRoleDao;
import net.hs.easyj.saas.model.UserRole;
import org.springframework.stereotype.Repository;

/**
 * 用户角色 Dao 实现
 *
 * @author Gavin Hu
 * @create 2015/8/26
 */
@Repository
public class UserRoleDaoImpl extends MyBatisDaoSupport<UserRole> implements UserRoleDao {
}
