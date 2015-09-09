package net.hs.easyj.saas.service.impl;

import net.hs.easyj.saas.dao.MenuDao;
import net.hs.easyj.saas.dao.UserMenuDao;
import net.hs.easyj.saas.model.Menu;
import net.hs.easyj.saas.model.UserMenu;
import net.hs.easyj.saas.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 菜单服务
 *
 * @author Gavin Hu
 * @create 2015/9/5
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;
    @Autowired
    private UserMenuDao userMenuDao;

    @Override
    public void createMenu(Menu menu) {
        menuDao.create(menu);
    }

    @Override
    public void createUserMenu(UserMenu userMenu) {
        userMenuDao.create(userMenu);
    }

    @Override
    public Menu loadMenu(Long tenantId, String code) {
        //
        Menu menu = menuDao.findOne(Menu.exampleOf(tenantId, code));
        //
        return menu;
    }

    //=======================================================================

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public void setUserMenuDao(UserMenuDao userMenuDao) {
        this.userMenuDao = userMenuDao;
    }
}
