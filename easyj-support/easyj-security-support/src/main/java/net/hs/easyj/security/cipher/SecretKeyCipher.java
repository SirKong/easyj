package net.hs.easyj.security.cipher;

import org.apache.commons.io.IOUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;

/**
 * 密钥 加密解密
 *
 * @author Gavin Hu
 * @create 2015/7/16
 */
public abstract class SecretKeyCipher extends AbstractCipher {

    private String algorithm;

    private SecretKey secretKey;

    private InputStream secretKeySource;

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setSecretKeySource(InputStream secretKeySource) {
        this.secretKeySource = secretKeySource;
    }

    @Override
    protected Key getKey() {
        if(secretKey==null) {
            synchronized (this) {
                if(secretKey==null) {
                    try {
                        this.secretKey = new SecretKeySpec(IOUtils.toByteArray(secretKeySource), algorithm);
                    } catch (IOException e) {
                        throw new CipherException("读取安全密钥 IO 异常", e);
                    }

                }
            }
        }
        return secretKey;
    }

}
