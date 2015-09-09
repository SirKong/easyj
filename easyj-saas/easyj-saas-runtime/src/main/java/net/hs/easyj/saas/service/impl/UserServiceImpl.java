package net.hs.easyj.saas.service.impl;

import net.hs.easyj.saas.dao.UserDao;
import net.hs.easyj.saas.dao.UserRoleDao;
import net.hs.easyj.saas.model.User;
import net.hs.easyj.saas.model.UserRole;
import net.hs.easyj.saas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务
 *
 * @author Gavin Hu
 * @create 2015/9/5
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public void createUser(User user) {
        userDao.create(user);
    }

    @Override
    public void assignUserRole(UserRole userRole) {
        userRoleDao.create(userRole);
    }

    //=======================================================================


    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }
}
