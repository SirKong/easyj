package net.hs.easyj.saas.service;

import net.hs.easyj.saas.dao.TenantDao;
import net.hs.easyj.saas.model.Tenant;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * 服务单元测试支持类
 *
 * @author Gavin Hu
 * @create 2015/8/27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/testContext.xml")
@TransactionConfiguration(defaultRollback = true)
public class ServiceTestSupport {

    @Autowired
    private TenantDao tenantDao;

    protected Tenant tenant;

    @Before
    public void beforeSetup() {
        this.tenant = new Tenant();
        this.tenant.setName("Hundsun");
        //
        tenantDao.create(tenant);
    }

}
