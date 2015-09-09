package net.hs.easyj.web.component.loader;

import javax.servlet.http.HttpServletRequest;

/**
 * UI组件 数据加载器
 *
 * @author Gavin Hu
 * @create 2015/5/22
 */
public interface UIComponentDataLoader {

    boolean support(HttpServletRequest request);

    Object loadData(HttpServletRequest request);

}
