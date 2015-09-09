package net.hs.easyj.web.widget.entry.loader;

/**
 * 页面配置加载器异常
 *
 * @author Gavin Hu
 * @create 2014/11/25
 */
public class PageLoaderException extends RuntimeException {

    public PageLoaderException(String message) {
        super(message);
    }

    public PageLoaderException(String message, Throwable cause) {
        super(message, cause);
    }

}
