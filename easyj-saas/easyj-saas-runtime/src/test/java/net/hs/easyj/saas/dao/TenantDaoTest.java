package net.hs.easyj.saas.dao;

import net.hs.easyj.saas.model.Tenant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 租户 Dao 单元测试
 *
 * @author Gavin Hu
 * @create 2015/8/26
 */
public class TenantDaoTest extends DaoTestSupport {

    @Autowired
    private TenantDao tenantDao;

    @Test
    public void testCreate() throws Exception {
        Tenant tenant = new Tenant();
        tenant.setName("Hundsun");
        //
        tenantDao.create(tenant);
    }

    @Test
    public void testRetrive() throws Exception {
        Tenant tenant = new Tenant();
        tenant.setName("Hundsun");
        //
        List<Tenant> tenants = tenantDao.findByExample(tenant);
        System.out.println(tenants.size());
    }
}
