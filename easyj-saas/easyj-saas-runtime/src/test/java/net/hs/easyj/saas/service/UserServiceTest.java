package net.hs.easyj.saas.service;

import net.hs.easyj.saas.model.Role;
import net.hs.easyj.saas.model.User;
import net.hs.easyj.saas.model.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户服务单元测试
 *
 * @author Gavin Hu
 * @create 2015/9/5
 */
public class UserServiceTest extends ServiceTestSupport {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    private User user;

    private Role role;

    @Before
    public void setUp() throws Exception {
        //
        this.user = new User();
        user.setTenantId(tenant.getId());
        user.setUsername("haozhonghu");
        user.setPassword("123456");
        userService.createUser(user);
        //
        this.role = new Role();
        role.setTenantId(tenant.getId());
        role.setCode("admin");
        role.setName("管理员");
        roleService.createRole(role);
    }

    @Test
    public void testAssignUserRole() throws Exception {
        UserRole userRole = new UserRole();
        userRole.setTenantId(tenant.getId());
        userRole.setUserId(user.getId());
        userRole.setRoleId(role.getId());
        //
        userService.assignUserRole(userRole);
    }
}
