package net.hs.easyj.web.component.exception;

/**
 * UI 组件资源未找到异常
 *
 * @author Gavin Hu
 * @create 2015/5/15
 */
public class UIComponentResourceNotFoundException extends RuntimeException {

    public UIComponentResourceNotFoundException(String message) {
        super(message);
    }

    public UIComponentResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
