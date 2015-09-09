package net.hs.easyj.saas.dao.impl;

import net.hs.easyj.dao.mybatis.spring.support.MyBatisDaoSupport;
import net.hs.easyj.saas.dao.WidgetDao;
import net.hs.easyj.saas.model.Widget;
import org.springframework.stereotype.Repository;

/**
 * 组件 Dao 实现
 *
 * @author Gavin Hu
 * @create 2015/8/27
 */
@Repository
public class WidgetDaoImpl extends MyBatisDaoSupport<Widget> implements WidgetDao {
}
