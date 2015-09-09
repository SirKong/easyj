package net.hs.easyj.security.cipher;

import org.junit.Before;
import org.junit.Test;
import sun.misc.IOUtils;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

/**
 * @author gavin
 * @create 15/7/16
 */
public class CipherTest {

    private PrivateKeyCipher privateKeyCipher;

    private PublicKeyCipher publicKeyCipher;

    @Before
    public void setup() {
        //
        InputStream keyStoreSource = getClass().getResourceAsStream("/czb_ca.pfx");
        //
        privateKeyCipher = new PrivateKeyCipher();
        privateKeyCipher.setKeyStoreType("PKCS12");
        privateKeyCipher.setCertificateAlias("czb_ca");
        privateKeyCipher.setKeyStorePassword("123456");
        privateKeyCipher.setKeyStoreSource(keyStoreSource);
        //
        InputStream certificateSource = getClass().getResourceAsStream("/czb_ca.cer");
        publicKeyCipher = new PublicKeyCipher();
        publicKeyCipher.setCertificateSource(certificateSource);
    }

    @Test
    public void testByteArrayEncryptAndDecrypt() {
        byte[] encrypted = privateKeyCipher.encrypt("huhaozhong".getBytes());
        System.out.println(new String(encrypted));
        //
        byte[] decrypted = publicKeyCipher.decrypt(encrypted);
        System.out.println(new String(decrypted));
    }

    @Test
    public void testInputStreamEncryptAndDecrypt() {
        //
        InputStream encrypted = privateKeyCipher.encrypt(getClass().getResourceAsStream("/init.sql"));
        //System.out.println(toString(encrypted));
        //
        InputStream decrypted = publicKeyCipher.decrypt(encrypted);
        System.out.println(toString(decrypted));
    }

    private String toString(InputStream inputStream) {
        StringBuffer sb = new StringBuffer();
        //
        byte[] buffer = new byte[4096];
        int nRead = -1;
        try {
            while((nRead=inputStream.read(buffer))!=-1) {
                sb.append(new String(buffer, 0, nRead));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
