package net.hs.easyj.security.cipher;

import java.io.InputStream;

/**
 * 加密解密
 *
 * @author Gavin Hu
 * @create 2015/7/15
 */
public interface Cipher {

    byte[] encrypt(byte[] decrypted);

    byte[] decrypt(byte[] encrypted);

    InputStream encrypt(InputStream decrypted);

    InputStream decrypt(InputStream encrypted);

}
