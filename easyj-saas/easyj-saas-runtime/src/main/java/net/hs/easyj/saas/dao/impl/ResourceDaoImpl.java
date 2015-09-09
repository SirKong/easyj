package net.hs.easyj.saas.dao.impl;

import net.hs.easyj.dao.mybatis.spring.support.MyBatisDaoSupport;
import net.hs.easyj.saas.dao.ResourceDao;
import net.hs.easyj.saas.model.Resource;
import org.springframework.stereotype.Repository;

/**
 * 资源 Dao 实现
 * @author Gavin Hu
 * @create 2015/8/26
 */
@Repository
public class ResourceDaoImpl extends MyBatisDaoSupport<Resource> implements ResourceDao {
}
