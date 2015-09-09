package net.hs.easyj.saas.service;

import net.hs.easyj.saas.model.Menu;
import net.hs.easyj.saas.model.UserMenu;

/**
 * 菜单服务
 *
 * @author Gavin Hu
 * @create 2015/9/5
 */
public interface MenuService {

    void createMenu(Menu menu);

    void createUserMenu(UserMenu userMenu);

    Menu loadMenu(Long tenantId, String code);

}
