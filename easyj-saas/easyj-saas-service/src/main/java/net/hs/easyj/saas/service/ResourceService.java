package net.hs.easyj.saas.service;

import net.hs.easyj.model.Page;
import net.hs.easyj.saas.model.Resource;

/**
 * 资源服务
 *
 * @author Gavin Hu
 * @create 2015/8/28
 */
public interface ResourceService {

    /**
     * 创建资源
     * @param resource
     */
    void createResource(Resource resource);

    /**
     * 分页查找
     * @param page
     * @return
     */
    Page<Resource> findPage(Page<Resource> page);

}
