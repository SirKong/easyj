package net.hs.easyj.saas.dao.impl;

import net.hs.easyj.dao.mybatis.spring.support.MyBatisDaoSupport;
import net.hs.easyj.saas.dao.QueryInDao;
import net.hs.easyj.saas.model.QueryIn;
import org.springframework.stereotype.Repository;

/**
 * 查询输入 Dao 实现
 *
 * @author Gavin Hu
 * @create 2015/9/5
 */
@Repository
public class QueryInDaoImpl extends MyBatisDaoSupport<QueryIn> implements QueryInDao {
}
