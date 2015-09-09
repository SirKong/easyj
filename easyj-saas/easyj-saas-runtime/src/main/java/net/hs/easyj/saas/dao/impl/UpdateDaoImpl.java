package net.hs.easyj.saas.dao.impl;

import net.hs.easyj.dao.mybatis.spring.support.MyBatisDaoSupport;
import net.hs.easyj.saas.dao.UpdateDao;
import net.hs.easyj.saas.model.Update;
import org.springframework.stereotype.Repository;

/**
 * 更新 Dao 实现
 *
 * @author Gavin Hu
 * @create 2015/9/5
 */
@Repository
public class UpdateDaoImpl extends MyBatisDaoSupport<Update> implements UpdateDao {
}
