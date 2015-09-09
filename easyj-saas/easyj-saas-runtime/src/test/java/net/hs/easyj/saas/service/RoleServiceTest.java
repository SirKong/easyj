package net.hs.easyj.saas.service;

import net.hs.easyj.saas.model.Resource;
import net.hs.easyj.saas.model.Role;
import net.hs.easyj.saas.model.RoleResource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 角色服务单元测试
 *
 * @author Gavin Hu
 * @create 2015/9/5
 */
public class RoleServiceTest extends ServiceTestSupport {

    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;

    private Role role;

    private Resource resource;

    @Before
    public void setUp() throws Exception {
        this.resource = new Resource();
        resource.setTenantId(tenant.getId());
        resource.setCode("view-user");
        resource.setName("查看用户");
        resourceService.createResource(resource);
        //
        this.role = new Role();
        role.setTenantId(tenant.getId());
        role.setCode("admin");
        role.setName("管理员");
        roleService.createRole(role);
    }

    @Test
    public void testAssignRoleResource() throws Exception {
        //
        RoleResource roleResource = new RoleResource();
        roleResource.setTenantId(tenant.getId());
        roleResource.setRoleId(role.getId());
        roleResource.setResourceId(resource.getId());
        //
        roleService.assignRoleResource(roleResource);
    }
}
