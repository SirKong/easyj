package net.hs.easyj.security.cipher;

/**
 * DES 加密解密
 *
 * @author Gavin Hu
 * @create 15/7/16
 */
public class DESCipher extends SecretKeyCipher {

    private static final String ALGORITHM = "DES";

    public DESCipher() {
        super.setAlgorithm(ALGORITHM);
    }

}
