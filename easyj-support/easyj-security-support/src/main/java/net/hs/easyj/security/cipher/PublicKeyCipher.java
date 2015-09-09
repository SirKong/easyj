package net.hs.easyj.security.cipher;

import java.io.InputStream;
import java.security.Key;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

/**
 * 公钥 加密解密
 *
 * @author Gavin Hu
 * @create 2015/7/16
 */
public class PublicKeyCipher extends AbstractCipher {

    private Certificate certificate;

    private InputStream certificateSource;

    public void setCertificateSource(InputStream certificateSource) {
        this.certificateSource = certificateSource;
    }

    protected Certificate getCertificate() {
        if(certificate==null) {
            synchronized (this) {
                if(certificate==null) {
                    CertificateFactory certificateFactory = null;
                    try {
                        certificateFactory = CertificateFactory.getInstance("X.509");
                        this.certificate = certificateFactory.generateCertificate(certificateSource);
                    } catch (CertificateException e) {
                        throw new CipherException("加载证书异常", e);
                    }
                }
            }
        }
        return certificate;
    }

    protected PublicKey getPublicKey() {
        return getCertificate().getPublicKey();
    }

    @Override
    protected Key getKey() {
        return getPublicKey();
    }

}
