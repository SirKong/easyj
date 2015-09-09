package net.hs.easyj.web.extender.config;

import java.util.List;
import java.util.Map;

/**
 * Created by Gavin Hu on 2015/2/6.
 */
public interface WebConfiguration {

    List<FilterConfig> getAllFilterConfig();

    List<ServletConfig> getAllServletConfig();

    List<ListenerConfig> getAllListenerConfig();

    Map<String, String> getContextParams();

}
