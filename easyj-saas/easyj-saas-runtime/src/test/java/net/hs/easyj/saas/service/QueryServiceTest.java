package net.hs.easyj.saas.service;

import net.hs.easyj.saas.dao.QueryDao;
import net.hs.easyj.saas.dao.QueryInDao;
import net.hs.easyj.saas.dao.QueryOutDao;
import net.hs.easyj.saas.model.Query;
import net.hs.easyj.saas.model.QueryIn;
import net.hs.easyj.saas.model.QueryOut;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 通用查询服务单元测试
 *
 * @author Gavin Hu
 * @create 2015/9/6
 */
public class QueryServiceTest extends ServiceTestSupport {

    @Autowired
    private QueryService queryService;
    @Autowired
    private QueryDao queryDao;
    @Autowired
    private QueryInDao queryInDao;
    @Autowired
    private QueryOutDao queryOutDao;

    @Before
    public void setUp() throws Exception {
        Query query = new Query();
        query.setTenantId(tenant.getId());
        query.setCode("view-user");
        query.setTitle("查看用户");
        query.setVersion("1.0.0");
        query.setEnabled(true);
        query.setQuery("select * from `user`");
        query.setViewTemplate("/template/view-user.html");
        queryDao.create(query);
        //
        QueryIn queryIn = new QueryIn();
        queryIn.setTenantId(tenant.getId());
        queryIn.setQueryId(query.getId());
        queryIn.setName("username");
        queryIn.setLabel("账号");
        queryInDao.create(queryIn);
        //
        QueryOut queryOut = new QueryOut();
        queryOut.setTenantId(tenant.getId());
        queryOut.setQueryId(tenant.getId());
        queryOut.setName("password");
        queryOut.setLabel("密码");
        queryOutDao.create(queryOut);
    }

    @Test
    public void testLoadQuery() throws Exception {
        //
        Query query = queryService.loadQuery(tenant.getId(), "view-user");
        System.out.println(query.getTitle());
    }
}
