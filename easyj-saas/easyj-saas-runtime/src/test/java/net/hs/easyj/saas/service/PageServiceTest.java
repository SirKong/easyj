package net.hs.easyj.saas.service;

import net.hs.easyj.saas.model.WidgetEvent;
import net.hs.easyj.saas.model.Page;
import net.hs.easyj.saas.model.PagePosition;
import net.hs.easyj.saas.model.Widget;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author gavin
 * @create 15/8/27
 */
public class PageServiceTest extends ServiceTestSupport {

    @Autowired
    private PageService pageService;

    @Before
    public void setup() {
        //
        Page page = new Page();
        page.setTitle("首页");
        page.setPath("/index.html");
        page.setTenantId(tenant.getId());
        page.setLayout("layout_login");
        pageService.createPage(page);
        //
        PagePosition position = new PagePosition();
        position.setTenantId(tenant.getId());
        position.setPageId(page.getId());
        position.setName("main");
        pageService.createPosition(position);
        //
        Widget widgetLogin = new Widget();
        widgetLogin.setTenantId(tenant.getId());
        widgetLogin.setPath("/system/login.htm");
        widgetLogin.setPositionId(position.getId());
        pageService.createWidget(widgetLogin);
        //
        WidgetEvent eventLogin = new WidgetEvent();
        eventLogin.setTenantId(tenant.getId());
        eventLogin.setWidgetId(widgetLogin.getId());
        eventLogin.setName("login-success");
        eventLogin.setExpression("/dashboard.html");
        pageService.createEvent(eventLogin);
    }

    @Test
    public void testLoadPage() throws Exception {
        //
        Page page = pageService.loadPage(tenant.getId(), "/index.html");
        Assert.assertNotNull(page);
    }
}
