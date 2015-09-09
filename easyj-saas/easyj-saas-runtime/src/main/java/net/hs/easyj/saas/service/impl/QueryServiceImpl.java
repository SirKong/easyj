package net.hs.easyj.saas.service.impl;

import net.hs.easyj.saas.dao.QueryDao;
import net.hs.easyj.saas.dao.QueryInDao;
import net.hs.easyj.saas.dao.QueryOutDao;
import net.hs.easyj.saas.model.Query;
import net.hs.easyj.saas.model.QueryIn;
import net.hs.easyj.saas.model.QueryOut;
import net.hs.easyj.saas.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查询服务实现
 *
 * @author Gavin Hu
 * @create 2015/9/5
 */
@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    private QueryDao queryDao;
    @Autowired
    private QueryInDao queryInDao;
    @Autowired
    private QueryOutDao queryOutDao;

    @Override
    public Query loadQuery(Long tenantId, String code) {
        //
        Query query = queryDao.findOne(Query.exampleOf(tenantId, code));
        if(query!=null) {
            List<QueryIn> queryIns = queryInDao.findByExample(QueryIn.exampleOf(tenantId, query.getId()));
            query.setQueryIns(queryIns);
            //
            List<QueryOut> queryOuts = queryOutDao.findByExample(QueryOut.exampleOf(tenantId, query.getId()));
            query.setQueryOuts(queryOuts);
        }
        //
        return query;
    }

    //=======================================================================

    public void setQueryDao(QueryDao queryDao) {
        this.queryDao = queryDao;
    }

    public void setQueryInDao(QueryInDao queryInDao) {
        this.queryInDao = queryInDao;
    }

    public void setQueryOutDao(QueryOutDao queryOutDao) {
        this.queryOutDao = queryOutDao;
    }
}
