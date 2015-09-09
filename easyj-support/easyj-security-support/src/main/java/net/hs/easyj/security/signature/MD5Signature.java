package net.hs.easyj.security.signature;

/**
 * MD5 签名验签
 *
 * @author Gavin Hu
 * @create 2015/7/16
 */
public class MD5Signature extends AbstractSignature {

    public MD5Signature() {
        super.setAlgorithmName("MD5");
    }

}
