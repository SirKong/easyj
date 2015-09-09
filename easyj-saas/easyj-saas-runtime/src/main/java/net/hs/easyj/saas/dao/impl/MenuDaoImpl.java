package net.hs.easyj.saas.dao.impl;

import net.hs.easyj.dao.mybatis.spring.support.MyBatisDaoSupport;
import net.hs.easyj.saas.dao.MenuDao;
import net.hs.easyj.saas.model.Menu;
import org.springframework.stereotype.Repository;

/**
 * 菜单 Dao 实现
 *
 * @author Gavin Hu
 * @create 2015/8/26
 */
@Repository
public class MenuDaoImpl extends MyBatisDaoSupport<Menu> implements MenuDao {
}
