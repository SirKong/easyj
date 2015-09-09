package net.hs.easyj.saas.service;


import net.hs.easyj.saas.model.WidgetEvent;
import net.hs.easyj.saas.model.Page;
import net.hs.easyj.saas.model.PagePosition;
import net.hs.easyj.saas.model.Widget;

/**
 * 页面服务
 *
 * @author Gavin Hu
 * @create 2015/8/27
 */
public interface PageService {

    /**
     * 根据租户 ID 和 页面 Path 加载页面 (重量级实现，性能较低)
     * @param tenantId
     * @param path
     * @return
     */
    Page loadPage(Long tenantId, String path);

    /**
     * 创建页面
     * @param page
     */
    void createPage(Page page);

    /**
     * 创建位置
     * @param position
     */
    void createPosition(PagePosition position);

    /**
     * 创建组件
     * @param widget
     */
    void createWidget(Widget widget);

    /**
     * 创建组件事件
     * @param event
     */
    void createEvent(WidgetEvent event);

}
