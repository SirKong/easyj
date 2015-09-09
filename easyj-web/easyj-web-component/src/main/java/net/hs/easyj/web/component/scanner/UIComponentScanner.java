package net.hs.easyj.web.component.scanner;

import java.util.Set;

/**
 * UI 组件扫描器
 *
 * @author Gavin
 * @create 2015/5/6
 */
public interface UIComponentScanner {

    /**
     * 扫描 UI 组件
     * @param viewName 扫描指定视图中的组件
     * @return 返回扫描到的 UI 组件名集合
     */
    Set<String> scan(String viewName);

}
