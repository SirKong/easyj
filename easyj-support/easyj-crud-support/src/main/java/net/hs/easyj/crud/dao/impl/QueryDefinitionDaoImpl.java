package net.hs.easyj.crud.dao.impl;

import net.hs.easyj.dao.mybatis.spring.support.MyBatisDaoSupport;
import net.hs.easyj.crud.dao.QueryDefinitionDao;
import net.hs.easyj.crud.model.QueryDefinition;
import org.springframework.stereotype.Repository;

@Repository
public class QueryDefinitionDaoImpl extends MyBatisDaoSupport<QueryDefinition> implements QueryDefinitionDao {

}
