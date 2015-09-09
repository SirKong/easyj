package net.hs.easyj.saas.service.impl;

import net.hs.easyj.model.Page;
import net.hs.easyj.saas.dao.ResourceDao;
import net.hs.easyj.saas.model.Resource;
import net.hs.easyj.saas.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 资源服务
 *
 * @author Gavin Hu
 * @create 2015/9/5
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Override
    public void createResource(Resource resource) {
        resourceDao.create(resource);
    }

    @Override
    public Page<Resource> findPage(Page<Resource> page) {
        return resourceDao.findPage(page);
    }

    //=======================================================================

    public void setResourceDao(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }
}
