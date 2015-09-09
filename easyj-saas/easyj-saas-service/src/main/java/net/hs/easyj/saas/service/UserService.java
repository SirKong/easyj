package net.hs.easyj.saas.service;

import net.hs.easyj.saas.model.User;
import net.hs.easyj.saas.model.UserRole;

/**
 * 用户服务
 *
 * @author Gavin Hu
 * @create 2015/8/28
 */
public interface UserService {

    /**
     * 创建用户
     * @param user
     */
    void createUser(User user);

    /**
     * 分配用户角色
     * @param userRole
     */
    void assignUserRole(UserRole userRole);

}
