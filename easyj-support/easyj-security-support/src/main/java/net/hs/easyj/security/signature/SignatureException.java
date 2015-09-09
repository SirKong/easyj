package net.hs.easyj.security.signature;

/**
 * 签名验签异常
 *
 * @author Gavin Hu
 * @create 2015/7/16
 */
public class SignatureException extends RuntimeException {

    public SignatureException(String message) {
        super(message);
    }

    public SignatureException(String message, Throwable cause) {
        super(message, cause);
    }

}
