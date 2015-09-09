package net.hs.easyj.security.cipher;

import net.hs.easyj.security.signature.SignatureException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

/**
 * 私钥 加密解密
 *
 * @author Gavin Hu
 * @create 2015/7/16
 */
public class PrivateKeyCipher extends AbstractCipher {

    private String DEFAULT_KEY_STORE_TYPE = "JKS";

    private KeyStore keyStore;

    private InputStream keyStoreSource;

    private char[] keyStorePassword;

    private String keyStoreType = DEFAULT_KEY_STORE_TYPE;

    private String certificateAlias;

    /**
     * 设置 KeyStore 类型 (JKS PKCS12)
     *
     * @param keyStoreType
     */
    public void setKeyStoreType(String keyStoreType) {
        this.keyStoreType = keyStoreType;
    }

    /**
     * 设置 KeyStore 密码
     *
     * @param keyStorePassword
     */
    public void setKeyStorePassword(String keyStorePassword) {
        this.keyStorePassword = keyStorePassword.toCharArray();
    }

    /**
     * 设置证书别名
     *
     * @param certificateAlias
     */
    public void setCertificateAlias(String certificateAlias) {
        this.certificateAlias = certificateAlias;
    }

    /**
     * 设置 KeyStore 源
     *
     * @param keyStoreSource
     */
    public void setKeyStoreSource(InputStream keyStoreSource) {
        this.keyStoreSource = keyStoreSource;
    }


    protected KeyStore getKeyStore() {
        if (keyStore == null) {
            synchronized (this) {
                if (keyStore == null) {
                    if ("PKCS12".equals(keyStoreType)) {
                        Security.addProvider(new BouncyCastleProvider());
                    }
                    //
                    try {
                        this.keyStore = KeyStore.getInstance(keyStoreType);
                        keyStore.load(keyStoreSource, "123456".toCharArray());
                    } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
                        throw new SignatureException("获取 KeyStore 异常！", e);
                    }
                }
            }
        }
        //
        return keyStore;
    }

    protected PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) getKeyStore().getKey(certificateAlias, keyStorePassword);
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new SignatureException("获取 PrivateKey 异常！", e);
        }
    }

    protected PublicKey getPublicKey() {
        try {
            Certificate certificate = getKeyStore().getCertificate(certificateAlias);
            return certificate.getPublicKey();
        } catch (KeyStoreException e) {
            throw new SignatureException("获取 PublicKey 异常！", e);
        }
    }

    @Override
    protected Key getKey() {
        return getPrivateKey();
    }

}
