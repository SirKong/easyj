package net.hs.easyj.web.widget.entry.loader;


import net.hs.easyj.web.widget.entry.config.PageConfig;

import java.util.Map;

/**
 * 页面配置加载器
 *
 * @author Gavin Hu
 * @create 2014/11/25
 */
public interface PageLoader {

    PageConfig load(String path, Map<String, String> params);

}
