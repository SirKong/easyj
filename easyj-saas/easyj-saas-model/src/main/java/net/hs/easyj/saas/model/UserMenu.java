package net.hs.easyj.saas.model;

/**
 * 用户菜单模型
 *
 * @author Gavin Hu
 * @create 2015/8/26
 */
public class UserMenu extends Id {

    private Long tenantId;

    private Long userId;

    private Long menuId;

    private User user;

    private Menu menu;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
