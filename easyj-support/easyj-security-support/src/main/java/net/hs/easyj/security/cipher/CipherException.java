package net.hs.easyj.security.cipher;

/**
 * 加密解密异常
 *
 * @author Gavin Hu
 * @create 2015/7/16
 */
public class CipherException extends RuntimeException {

    public CipherException(String message) {
        super(message);
    }

    public CipherException(String message, Throwable cause) {
        super(message, cause);
    }

}
