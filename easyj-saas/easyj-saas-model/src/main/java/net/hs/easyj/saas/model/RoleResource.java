package net.hs.easyj.saas.model;

/**
 * 角色资源关系模型
 *
 * @author Gavin Hu
 * @create 2015/8/26
 */
public class RoleResource extends Id {

    private Long tenantId;

    private Long roleId;

    private Long resourceId;

    private Role role;

    private Resource resource;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
