package net.hs.easyj.saas.service.impl;

import net.hs.easyj.saas.dao.RoleDao;
import net.hs.easyj.saas.dao.RoleResourceDao;
import net.hs.easyj.saas.model.Role;
import net.hs.easyj.saas.model.RoleResource;
import net.hs.easyj.saas.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 角色服务
 *
 * @author Gavin Hu
 * @create 2015/9/5
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleResourceDao roleResourceDao;

    @Override
    public void createRole(Role role) {
        roleDao.create(role);
    }

    @Override
    public void assignRoleResource(RoleResource roleResource) {
        roleResourceDao.create(roleResource);
    }

    //=======================================================================


    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void setRoleResourceDao(RoleResourceDao roleResourceDao) {
        this.roleResourceDao = roleResourceDao;
    }
}
