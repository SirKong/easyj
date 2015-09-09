package net.hs.easyj.saas.dao.impl;

import net.hs.easyj.dao.mybatis.spring.support.MyBatisDaoSupport;
import net.hs.easyj.saas.dao.PageDao;
import net.hs.easyj.saas.model.Page;
import org.springframework.stereotype.Repository;

/**
 * 页面 Dao 实现
 *
 * @author Gavin Hu
 * @create 2015/8/27
 */
@Repository
public class PageDaoImpl extends MyBatisDaoSupport<Page> implements PageDao {
}
