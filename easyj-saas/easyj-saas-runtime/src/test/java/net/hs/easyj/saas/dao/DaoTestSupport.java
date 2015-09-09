package net.hs.easyj.saas.dao;

import net.hs.easyj.saas.model.Tenant;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Dao 单元测试
 *
 * @author Gavin Hu
 * @create 2015/8/26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/testContext.xml")
@TransactionConfiguration(defaultRollback = true)
public class DaoTestSupport {

    @Autowired
    private TenantDao tenantDao;

    protected Tenant tenant;

    @Before
    public void setup() {
        this.tenant = new Tenant();
        this.tenant.setName("Hundsun");
        //
        tenantDao.create(tenant);
    }

}
