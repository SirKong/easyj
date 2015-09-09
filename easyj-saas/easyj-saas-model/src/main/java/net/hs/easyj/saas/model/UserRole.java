package net.hs.easyj.saas.model;

/**
 * 用户角色关系模型
 *
 * @author Gavin Hu
 * @create 15/8/26
 */
public class UserRole extends Id {

    private Long tenantId;

    private Long userId;

    private Long roleId;

    private User user;

    private Role role;

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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
