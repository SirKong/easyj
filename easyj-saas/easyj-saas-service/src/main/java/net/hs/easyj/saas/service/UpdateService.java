package net.hs.easyj.saas.service;

import net.hs.easyj.saas.model.Update;

/**
 * 更新服务
 *
 * @author Gavin Hu
 * @create 2015/9/5
 */
public interface UpdateService {

    /**
     * 根据租户 ID 和 code 加载更新
     * @param tenantId
     * @param code
     * @return
     */
    Update loadUpdate(Long tenantId, String code);

}
