package net.hs.easyj.web.component.exception;

/**
 * UIComponent 不存在异常
 *
 * @author Gavin Hu
 * @create 2015/5/8
 */
public class NoSuchUIComponentException extends RuntimeException {

    public NoSuchUIComponentException(String message) {
        super(message);
    }

    public NoSuchUIComponentException(String message, Throwable cause) {
        super(message, cause);
    }

}
