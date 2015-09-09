package net.hs.easyj.saas.service;

import net.hs.easyj.saas.model.Query;

/**
 * 查询服务
 *
 * @author Gavin Hu
 * @create 2015/9/5
 */
public interface QueryService {

    /**
     * 根据租户 ID 和 code 加载查询
     * @param tenantId
     * @param code
     * @return
     */
    Query loadQuery(Long tenantId, String code);

}
