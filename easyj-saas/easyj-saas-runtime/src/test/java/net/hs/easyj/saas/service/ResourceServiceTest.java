package net.hs.easyj.saas.service;

import net.hs.easyj.model.Page;
import net.hs.easyj.saas.model.Resource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 资源服务单元测试
 *
 * @author Gavin Hu
 * @create 2015/9/5
 */
public class ResourceServiceTest extends ServiceTestSupport {

    @Autowired
    private ResourceService resourceService;

    @Before
    public void setUp() throws Exception {
        Resource resource = new Resource();
        resource.setTenantId(tenant.getId());
        resource.setCode("view-user");
        resource.setName("查看用户");
        //
        resourceService.createResource(resource);
    }

    @Test
    public void testFindPage() throws Exception {
        Page page = new Page(10);
        page = resourceService.findPage(page);
        //
        System.out.println(page.getResults().size());
    }

}
