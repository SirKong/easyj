package net.hs.easyj.saas.service.impl;

import net.hs.easyj.saas.dao.WidgetEventDao;
import net.hs.easyj.saas.dao.PageDao;
import net.hs.easyj.saas.dao.PagePositionDao;
import net.hs.easyj.saas.dao.WidgetDao;
import net.hs.easyj.saas.model.WidgetEvent;
import net.hs.easyj.saas.model.Page;
import net.hs.easyj.saas.model.PagePosition;
import net.hs.easyj.saas.model.Widget;
import net.hs.easyj.saas.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 页面服务实现
 *
 * @author Gavin Hu
 * @create 2015/8/27
 */
@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private PageDao pageDao;
    @Autowired
    private PagePositionDao pagePositionDao;
    @Autowired
    private WidgetDao widgetDao;
    @Autowired
    private WidgetEventDao widgetEventDao;

    @Override
    public Page loadPage(Long tenantId, String path) {
        //
        Page page = pageDao.findOne(Page.exampleOf(tenantId, path));
        if(page!=null && page.getParentId()!=null) {
            Page parent = pageDao.retrieve(page.getParentId());
            page.setParent(parent);
        }
        return page;
    }

    @Override
    public void createPage(Page page) {
        //
        pageDao.create(page);
    }

    @Override
    public void createPosition(PagePosition position) {
        //
        pagePositionDao.create(position);
    }

    @Override
    public void createWidget(Widget widget) {
        //
        widgetDao.create(widget);
    }

    @Override
    public void createEvent(WidgetEvent event) {
        //
        widgetEventDao.create(event);
    }

    //=======================================================================


    public void setPageDao(PageDao pageDao) {
        this.pageDao = pageDao;
    }

    public void setPagePositionDao(PagePositionDao pagePositionDao) {
        this.pagePositionDao = pagePositionDao;
    }

    public void setWidgetDao(WidgetDao widgetDao) {
        this.widgetDao = widgetDao;
    }

    public void setWidgetEventDao(WidgetEventDao widgetEventDao) {
        this.widgetEventDao = widgetEventDao;
    }


}
