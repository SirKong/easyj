package net.hs.easyj.web.platform.widget;

import net.hs.easyj.web.widget.annotation.ActionMapping;
import net.hs.easyj.web.widget.annotation.ViewMapping;
import net.hs.easyj.web.widget.annotation.Widget;

/**
 *
 *
 * @author gavin
 * @create 15/8/24
 */
@Widget
public class AdminWidget {

    @ViewMapping("/admin/login")
    public void loginView() {
        //

    }

    @ActionMapping("/")
    public void loginAction() {

    }

}
