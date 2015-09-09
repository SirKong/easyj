package net.hs.easyj.security;

import org.junit.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * @author gavin
 * @create 15/7/16
 */
public class MessageDigestTest {

    @Test
    public void testDes() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
            SecretKey secretKey = keyGenerator.generateKey();
            //
            System.out.println(encodeHex(secretKey.getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDigest() throws Exception {
        //
        File bigFile = new File("/Users/gavin/Downloads/update.zip");
        FileInputStream bigStream = new FileInputStream(bigFile);
        //
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        //
        byte[] buffer = new byte[10240];
        int nRead = 0;
        while((nRead=bigStream.read(buffer))>0) {//8fe8aec8c113fc3db28456ae53bf8236
            messageDigest.update(buffer, 0, nRead);//60b85d6af7acb4298daf942af601b923
        }//
        //
        byte[] hashValue = messageDigest.digest();
        System.out.println(encodeHex(hashValue));
    }

    private static final char[] HEX_CHARS =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static char[] encodeHex(byte[] bytes) {
        char chars[] = new char[32];
        for (int i = 0; i < chars.length; i = i + 2) {
            byte b = bytes[i / 2];
            chars[i] = HEX_CHARS[(b >>> 0x4) & 0xf];
            chars[i + 1] = HEX_CHARS[b & 0xf];
        }
        return chars;
    }
}
