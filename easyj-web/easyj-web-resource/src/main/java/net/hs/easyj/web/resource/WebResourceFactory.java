package net.hs.easyj.web.resource;

/**
 * Web 资源工厂
 */
public interface WebResourceFactory {

    /**
     * 根据名称加载 Web 资源
     * @param name web 资源名
     * @param version web 资源版本  版本为空时去最近版本
     * @return
     */
    WebResource load(String name, String version);

}
