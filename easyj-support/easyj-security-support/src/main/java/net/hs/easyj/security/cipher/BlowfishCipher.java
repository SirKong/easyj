package net.hs.easyj.security.cipher;

/**
 * Blowfish 加密解密
 *
 * @author Gavin Hu
 * @create 15/7/16
 */
public class BlowfishCipher extends SecretKeyCipher {

    private static final String ALGORITHM = "Blowfish";

    public BlowfishCipher() {
        super.setAlgorithm(ALGORITHM);
    }

}
