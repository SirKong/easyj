package net.hs.easyj.web.component.resource;

import net.hs.easyj.web.component.exception.UIComponentResourceNotFoundException;

/**
 * UI 组件资源加载起
 *
 * @author Gavin Hu
 * @create 2015/5/15
 */
public interface UIComponentResourceLoader {

    UIComponentResource load(String name) throws UIComponentResourceNotFoundException;

}
