package net.hs.easyj.web.platform.widget;

import net.hs.easyj.web.platform.bean.LoginBean;
import net.hs.easyj.web.widget.annotation.ActionMapping;
import net.hs.easyj.web.widget.annotation.ViewMapping;
import net.hs.easyj.web.widget.annotation.Widget;
import net.hs.easyj.web.widget.handler.event.EventBus;

/**
 * @author gavin
 * @create 15/8/22
 */
@Widget
public class LoginWidget {

    @ViewMapping("/system/login")
    public void loginView() {
    }

    @ActionMapping("/system/login")
    public void loginAction(EventBus eventBus, LoginBean loginBean) {
        //
        eventBus.withData("loginBean", loginBean);
        //
        eventBus.trigger("login-success");
    }

}
