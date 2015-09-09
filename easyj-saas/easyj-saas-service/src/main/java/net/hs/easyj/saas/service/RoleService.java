package net.hs.easyj.saas.service;

import net.hs.easyj.saas.model.Role;
import net.hs.easyj.saas.model.RoleResource;

/**
 * 角色服务
 *
 * @author Gavin Hu
 * @create 2015/8/28
 */
public interface RoleService {

    /**
     * 创建角色
     * @param role
     */
    void createRole(Role role);

    /**
     * 分配角色资源
     * @param roleResource
     */
    void assignRoleResource(RoleResource roleResource);

}
