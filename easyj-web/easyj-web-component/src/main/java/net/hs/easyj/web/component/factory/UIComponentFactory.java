package net.hs.easyj.web.component.factory;

import net.hs.easyj.web.component.UIComponent;

/**
 * UI 组件工厂
 *
 * @author Gavin Hu
 * @create 2015/5/6
 */
public interface UIComponentFactory {

    UIComponent get(String name);

}
