package net.hs.easyj.saas.dao.impl;

import net.hs.easyj.dao.mybatis.spring.support.MyBatisDaoSupport;
import net.hs.easyj.saas.dao.TenantDao;
import net.hs.easyj.saas.model.Tenant;
import org.springframework.stereotype.Repository;

/**
 * 租户 Dao 实现
 *
 * @author Gavin Hu
 * @create 2015/8/26
 */
@Repository
public class TenantDaoImpl extends MyBatisDaoSupport<Tenant> implements TenantDao {
}
