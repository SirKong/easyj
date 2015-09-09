package net.hs.easyj.security.cipher;

/**
 * 3DES 加密解密
 *
 * @author Gavin Hu
 * @create 15/7/16
 */
public class DESedeCipher extends SecretKeyCipher {

    private static final String ALGORITHM = "DESede";

    public DESedeCipher() {
        super.setAlgorithm(ALGORITHM);
    }

}
