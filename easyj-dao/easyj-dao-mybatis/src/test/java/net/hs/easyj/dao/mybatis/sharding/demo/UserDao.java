package net.hs.easyj.dao.mybatis.sharding.demo;

import net.hs.easyj.dao.api.BaseDao;
import net.hs.easyj.model.Page;

/**
 * @author gavin
 * @create 15/6/21
 */
public interface UserDao extends BaseDao<User> {

    Page<User> findPage(Page page, String name);

}
