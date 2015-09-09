package net.hs.easyj.saas.dao.impl;

import net.hs.easyj.dao.mybatis.spring.support.MyBatisDaoSupport;
import net.hs.easyj.saas.dao.UserMenuDao;
import net.hs.easyj.saas.model.UserMenu;
import org.springframework.stereotype.Repository;

/**
 * 用户菜单 Dao 实现
 *
 * @author Gavin Hu
 * @create 2015/8/26
 */
@Repository
public class UserMenuDaoImpl extends MyBatisDaoSupport<UserMenu> implements UserMenuDao {
}
