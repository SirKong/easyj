package net.hs.easyj.saas.service;

import net.hs.easyj.saas.model.Menu;
import net.hs.easyj.saas.model.User;
import net.hs.easyj.saas.model.UserMenu;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author gavin
 * @create 15/9/5
 */
public class MenuServiceTest extends ServiceTestSupport {

    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        //
        Menu menu = new Menu();
        menu.setTenantId(tenant.getId());
        menu.setCode("main-menu");
        menu.setName("首页");
        menu.setUrl("/index.html");
        menuService.createMenu(menu);
        //
        Menu subMenu1 = new Menu();
        subMenu1.setTenantId(tenant.getId());
        subMenu1.setName("首页");
        subMenu1.setUrl("/index.html");
        subMenu1.setParentId(menu.getId());
        menuService.createMenu(subMenu1);
        //
        Menu subMenu2 = new Menu();
        subMenu2.setTenantId(tenant.getId());
        subMenu2.setName("首页");
        subMenu2.setUrl("/index.html");
        subMenu2.setParentId(menu.getId());
        menuService.createMenu(subMenu2);
    }

    @Test
    public void testLoadMenu() throws Exception {
        //
        Menu mainMenu = menuService.loadMenu(tenant.getId(), "main-menu");
        System.out.println(mainMenu.getCode());
    }

    @Test
    public void testLoadUserMenu() throws Exception {
        //
        User user = new User();
        user.setTenantId(tenant.getId());
        user.setUsername("haozhonghu");
        user.setPassword("123456");
        userService.createUser(user);
        //
        Menu mainMenu = menuService.loadMenu(tenant.getId(), "main-menu");
        //
        UserMenu userMenu = new UserMenu();
        userMenu.setTenantId(tenant.getId());
        userMenu.setUserId(user.getId());
        userMenu.setMenuId(mainMenu.getId());
        menuService.createUserMenu(userMenu);
        //

    }
}
