package net.hs.easyj.web.component;

/**
 * UI 组件
 *
 * @author Gavin Hu
 * @create 2015/5/6
 */
public interface UIComponent {

    /**
     * 是否为异步组件
     * 如果为异步组件，则数据通过异步加载，而元数据仍然同步加载
     * @return
     */
    boolean isAsync();

    /**
     * 组件名称
     * @return
     */
    String getName();

    /**
     * 获取原数据 KEY
     * @return
     */
    String getMetaKey();

    /**
     * 获取数据 KEY
     * @return
     */
    String getDataKey();

    /**
     * 获取元数据
     * @return
     */
    Object getMeta();

    /**
     * 获取数据
     * @return
     */
    Object getData();

}
