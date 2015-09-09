package net.hs.easyj.saas.service;

import net.hs.easyj.model.Page;
import net.hs.easyj.saas.model.Tenant;

/**
 * 租户服务
 *
 * @author Gavin Hu
 * @create 2015/8/27
 */
public interface TenantService {

    /**
     * 创建租户
     * @param tenant
     */
    void createTenant(Tenant tenant);

    /**
     * 分页查找租户
     * @param page
     * @return
     */
    Page<Tenant> findPage(Page<Tenant> page);

}
