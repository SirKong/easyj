package net.hs.easyj.security.signature;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 消息签名验签
 *
 *
 *
 * @author gavin
 * @create 15/7/16
 */
public abstract class AbstractSignature implements Signature {

    private String algorithmName;

    private int bufferSize = 4096;

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    @Override
    public byte[] sign(byte[] target) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithmName);
            return messageDigest.digest(target);
        } catch (NoSuchAlgorithmException e) {
            throw new SignatureException("签名失败：找不到"+algorithmName+"算法的 MessageDigest", e);
        }
    }

    @Override
    public boolean verify(byte[] target, byte[] signature) {
        byte[] _signature = sign(target);
        return equals(_signature, signature);
    }

    @Override
    public byte[] sign(InputStream target) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithmName);
            byte[] buffer = new byte[bufferSize];
            int nRead = 0;
            while((nRead=target.read(buffer))!=-1) {
                messageDigest.update(buffer, 0, nRead);
            }
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new SignatureException("签名失败：找不到"+algorithmName+"算法的 MessageDigest", e);
        } catch (IOException e) {
            throw new SignatureException("签名失败：目标 IO 异常", e);
        }
    }

    @Override
    public boolean verify(InputStream target, byte[] signature) {
        byte[] _signature = sign(target);
        return equals(_signature, signature);
    }

    private boolean equals(byte[] bytes1, byte[] bytes2) {
        if(bytes1.length!=bytes2.length) {
            return false;
        }
        for(int i=0; i<bytes1.length; i++) {
            if(bytes1[i]!=bytes2[i]) {
                return false;
            }
        }
        return true;
    }

}
