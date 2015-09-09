package net.hs.easyj.security.cipher;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 证书 加密解密
 * @author Gavin Hu
 * @create 2015/7/16
 */
public abstract class AbstractCipher implements Cipher {
    /* 最大文件加密块 */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    /* 最大文件解密块 */
    private static final int MAX_DECRYPT_BLOCK = 128;

    protected abstract Key getKey();

    @Override
    public byte[] encrypt(byte[] decrypted) {
        //
        Key key = getKey();
        //
        try {
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(key.getAlgorithm());
            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key);
            cipher.update(decrypted);
            // 对信息的数字签名
            return cipher.doFinal();
        } catch (IllegalBlockSizeException e) {
            throw new CipherException("", e);
        } catch (InvalidKeyException e) {
            throw new CipherException("", e);
        } catch (BadPaddingException e) {
            throw new CipherException("", e);
        } catch (NoSuchAlgorithmException e) {
            throw new CipherException("", e);
        } catch (NoSuchPaddingException e) {
            throw new CipherException("", e);
        }
    }

    @Override
    public byte[] decrypt(byte[] encrypted) {
        //
        Key key = getKey();
        //
        try {
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(key.getAlgorithm());
            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key);
            cipher.update(encrypted);
            // 对信息的数字签名
            return cipher.doFinal();
        } catch (IllegalBlockSizeException e) {
            throw new CipherException("", e);
        } catch (InvalidKeyException e) {
            throw new CipherException("", e);
        } catch (BadPaddingException e) {
            throw new CipherException("", e);
        } catch (NoSuchAlgorithmException e) {
            throw new CipherException("", e);
        } catch (NoSuchPaddingException e) {
            throw new CipherException("", e);
        }
    }

    @Override
    public InputStream encrypt(InputStream decrypted) {
        Key key = getKey();
        //
        try {
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(key.getAlgorithm());
            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key);
            //
            File tempFile = createTempFile();
            OutputStream encrypted = new FileOutputStream(tempFile);
            //
            byte[] buffer = new byte[MAX_ENCRYPT_BLOCK];
            while(decrypted.read(buffer)!=-1) {
                byte[] encryptedBuffer = cipher.doFinal(buffer);
                encrypted.write(encryptedBuffer, 0, encryptedBuffer.length);
                encrypted.flush();
                //
                buffer = new byte[MAX_ENCRYPT_BLOCK];
            }
            encrypted.close();
            //
            return new FileInputStream(tempFile);
        } catch (IllegalBlockSizeException e) {
            throw new CipherException("", e);
        } catch (InvalidKeyException e) {
            throw new CipherException("", e);
        } catch (BadPaddingException e) {
            throw new CipherException("", e);
        } catch (NoSuchAlgorithmException e) {
            throw new CipherException("", e);
        } catch (NoSuchPaddingException e) {
            throw new CipherException("", e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public InputStream decrypt(InputStream encrypted) {
        //
        Key key = getKey();
        //
        try {
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance(key.getAlgorithm());
            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key);
            //
            File tempFile = createTempFile();
            OutputStream decrypted = new FileOutputStream(tempFile);
            //
            byte[] buffer = new byte[MAX_DECRYPT_BLOCK];
            while(encrypted.read(buffer)!=-1) {
                byte[] decryptedBuffer = cipher.doFinal(buffer);
                decrypted.write(decryptedBuffer, 0, decryptedBuffer.length);
                decrypted.flush();
                //
                buffer = new byte[MAX_DECRYPT_BLOCK];
            }
            decrypted.close();
            //
            return new FileInputStream(tempFile);
        } catch (IllegalBlockSizeException e) {
            throw new CipherException("", e);
        } catch (InvalidKeyException e) {
            throw new CipherException("", e);
        } catch (BadPaddingException e) {
            throw new CipherException("", e);
        } catch (NoSuchAlgorithmException e) {
            throw new CipherException("", e);
        } catch (NoSuchPaddingException e) {
            throw new CipherException("", e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private File createTempFile() throws IOException {
        File tempFile = new File(System.getProperty("java.io.tmpdir"), UUID.randomUUID().toString());
        tempFile.createNewFile();
        return tempFile;
    }

}
