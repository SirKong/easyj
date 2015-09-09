package net.hs.easyj.saas.service.impl;

import net.hs.easyj.model.Page;
import net.hs.easyj.saas.dao.TenantDao;
import net.hs.easyj.saas.model.Tenant;
import net.hs.easyj.saas.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 租户服务实现
 * 
 * @author Gavin Hu
 * @create 2015/8/27
 */
@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantDao tenantDao;

    @Override
    public void createTenant(Tenant tenant) {
        //
        tenantDao.create(tenant);
    }

    @Override
    public Page<Tenant> findPage(Page<Tenant> page) {
        return tenantDao.findPage(page);
    }

    //=======================================================================

    public void setTenantDao(TenantDao tenantDao) {
        this.tenantDao = tenantDao;
    }

}
