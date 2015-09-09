package net.hs.easyj.saas.dao.impl;

import net.hs.easyj.dao.mybatis.spring.support.MyBatisDaoSupport;
import net.hs.easyj.saas.dao.RoleResourceDao;
import net.hs.easyj.saas.model.RoleResource;
import org.springframework.stereotype.Repository;

/**
 * 角色资源 Dao 实现
 *
 * @author Gavin Hu
 * @create 2015/8/26
 */
@Repository
public class RoleResourceDaoImpl extends MyBatisDaoSupport<RoleResource> implements RoleResourceDao {
}
