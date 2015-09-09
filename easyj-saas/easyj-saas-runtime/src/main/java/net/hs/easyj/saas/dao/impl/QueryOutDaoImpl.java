package net.hs.easyj.saas.dao.impl;

import net.hs.easyj.dao.mybatis.spring.support.MyBatisDaoSupport;
import net.hs.easyj.saas.dao.QueryOutDao;
import net.hs.easyj.saas.model.QueryOut;
import org.springframework.stereotype.Repository;

/**
 * 查询输出 Dao 实现
 *
 * @author Gavin Hu
 * @create 2015/9/5
 */
@Repository
public class QueryOutDaoImpl extends MyBatisDaoSupport<QueryOut> implements QueryOutDao {
}
