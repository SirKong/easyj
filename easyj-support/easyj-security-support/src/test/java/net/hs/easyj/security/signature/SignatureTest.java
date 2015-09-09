package net.hs.easyj.security.signature;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * 签名验签单元测试
 *
 * @author Gavin Hu
 * @create 2015/7/16
 */
public class SignatureTest {

    private Signature signature;

    @Before
    public void setup() {
        //
        this.signature = new MD5Signature();
    }

    @Test
    public void testByteArraySignAndVerify() {
        //
        byte[] target = "test signature".getBytes();
        //
        byte[] signValue = signature.sign(target);
        System.out.println(signValue);
        //
        boolean verifyValue = signature.verify(target, signValue);
        System.out.println(verifyValue);
    }

    @Test
    public void testInputStreamSignAndVerify() {
        //
        InputStream target = getClass().getResourceAsStream("/init.sql");
        //
        byte[] signValue = signature.sign(target);
        System.out.println(signValue);
        //
        target = getClass().getResourceAsStream("/init.sql");
        boolean verifyValue = signature.verify(target, signValue);
        System.out.println(verifyValue);
    }

}
