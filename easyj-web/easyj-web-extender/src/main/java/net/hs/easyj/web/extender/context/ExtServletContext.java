package net.hs.easyj.web.extender.context;

import javax.servlet.ServletContext;
import java.util.Map;

/**
 * Created by Gavin Hu on 2015/2/7.
 */
public interface ExtServletContext extends ServletContext {

    void setContextParams(Map<String, String> contextParams);

}
