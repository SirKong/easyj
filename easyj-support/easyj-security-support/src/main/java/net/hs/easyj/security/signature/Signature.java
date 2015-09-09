package net.hs.easyj.security.signature;

import java.io.InputStream;

/**
 * 签名验签
 *
 * @author Gavin Hu
 * @create 2015/7/15
 */
public interface Signature {

    /**
     * 签名
     * @param target
     * @return
     */
    byte[] sign(byte[] target);

    /**
     * 验签
     * @param target
     * @param signature
     * @return
     */
    boolean verify(byte[] target, byte[] signature);

    /**
     * 签名
     * @param target
     * @return
     */
    byte[] sign(InputStream target);

    /**
     * 验签
     * @param target
     * @param signature
     * @return
     */
    boolean verify(InputStream target, byte[] signature);

}
