package net.hs.easyj.web.component.resource;

import java.io.InputStream;

/**
 * UI 组件资源
 *
 * @author Gavin Hu
 * @create 2015/5/15
 */
public interface UIComponentResource {

    /**
     * 获取组件名
     * @return
     */
    String getComponentName();

    /**
     * 获取组件文档
     * @return
     */
    InputStream getComponentXDoc();

    /**
     * 获取组件Java类
     * @return
     */
    InputStream getComponentClass();

    /**
     * 获取组件界面
     * @return
     */
    InputStream getComponentUI();

    /**
     * 获取组件展现界面
     *
     * @return
     */
    InputStream getComponentShowUI();

    /**
     * 获取组件脚本
     * @return
     */
    InputStream getComponentScript();

    /**
     * 获取组件适配器脚本
     * @return
     */
    InputStream getComponentAdapterScript();

    /**
     * 获取组件校验脚本
     * @return
     */
    InputStream getComponentValidateScript();
}
