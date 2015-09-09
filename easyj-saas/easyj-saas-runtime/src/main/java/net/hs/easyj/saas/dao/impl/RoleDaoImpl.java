package net.hs.easyj.saas.dao.impl;

import net.hs.easyj.dao.mybatis.spring.support.MyBatisDaoSupport;
import net.hs.easyj.saas.dao.RoleDao;
import net.hs.easyj.saas.model.Role;
import org.springframework.stereotype.Repository;

/**
 * 角色 Dao 实现
 *
 * @author Gavin Hu
 * @create 2015/8/26
 */
@Repository
public class RoleDaoImpl extends MyBatisDaoSupport<Role> implements RoleDao {
}
