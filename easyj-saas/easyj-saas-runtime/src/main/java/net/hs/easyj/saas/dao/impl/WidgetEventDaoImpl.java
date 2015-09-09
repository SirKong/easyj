package net.hs.easyj.saas.dao.impl;

import net.hs.easyj.dao.mybatis.spring.support.MyBatisDaoSupport;
import net.hs.easyj.saas.dao.WidgetEventDao;
import net.hs.easyj.saas.model.WidgetEvent;
import org.springframework.stereotype.Repository;

/**
 * 事件 Dao 实现
 *
 * @author Gavin Hu
 * @create 2015/8/27
 */
@Repository
public class WidgetEventDaoImpl extends MyBatisDaoSupport<WidgetEvent> implements WidgetEventDao {
}
