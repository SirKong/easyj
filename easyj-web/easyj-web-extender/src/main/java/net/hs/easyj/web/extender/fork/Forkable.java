package net.hs.easyj.web.extender.fork;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 *
 * Created by Gavin Hu on 2015/2/26.
 */
public interface Forkable {

    void setServletContext(ServletContext context);

    boolean canFork(HttpServletRequest req);

    List<ForkedRequestAndResponse> fork(HttpServletRequest req, HttpServletResponse res);

}
