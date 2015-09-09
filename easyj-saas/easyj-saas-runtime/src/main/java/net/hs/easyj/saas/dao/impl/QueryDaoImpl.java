package net.hs.easyj.saas.dao.impl;

import net.hs.easyj.dao.mybatis.spring.support.MyBatisDaoSupport;
import net.hs.easyj.saas.dao.QueryDao;
import net.hs.easyj.saas.model.Query;
import org.springframework.stereotype.Repository;

/**
 * 查询 Dao 实现
 *
 * @author Gavin Hu
 * @create 2015/9/5
 */
@Repository
public class QueryDaoImpl extends MyBatisDaoSupport<Query> implements QueryDao {
}
