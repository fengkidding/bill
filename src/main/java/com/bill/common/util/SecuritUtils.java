package com.bill.common.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 安全验证，加密解密
 *
 * @author f
 * @#date 2020-01-13
 */
public class SecuritUtils {

    /**
     * aes秘钥
     */
    private static String aesKey = "fahwkelsjhfiu1232";

    /**
     * aes加密
     */
    private static Cipher cipherEncode;

    /**
     * aes解密
     */
    private static Cipher cipherDecode;

    /**
     * rsa秘钥
     */
    private static String rsaKey = "sdgreaar43534534";

    /**
     * rsa公钥加密
     */
    private static Cipher cipherPublicEncode;

    /**
     * rsa公钥解密
     */
    private static Cipher cipherPublicDecode;

    /**
     * rsa私钥加密
     */
    private static Cipher cipherPrivateEncode;

    /**
     * rsa私钥解密
     */
    private static Cipher cipherPrivateDecode;

    /**
     * 初始化方法
     */
    public static void init() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
        //aes
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom(aesKey.getBytes()));
        SecretKey secretKey = keyGenerator.generateKey();
        SecretKey generateKey = new SecretKeySpec(secretKey.getEncoded(), "AES");
        cipherEncode = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipherEncode.init(Cipher.ENCRYPT_MODE, generateKey);
        cipherDecode = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipherDecode.init(Cipher.DECRYPT_MODE, generateKey);

        //rsa
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512, new SecureRandom(rsaKey.getBytes()));
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        cipherPublicEncode = Cipher.getInstance("RSA");
        cipherPublicEncode.init(Cipher.ENCRYPT_MODE, publicKey);
        cipherPublicDecode = Cipher.getInstance("RSA");
        cipherPublicDecode.init(Cipher.DECRYPT_MODE, publicKey);
        cipherPrivateEncode = Cipher.getInstance("RSA");
        cipherPrivateEncode.init(Cipher.ENCRYPT_MODE, privateKey);
        cipherPrivateDecode = Cipher.getInstance("RSA");
        cipherPrivateDecode.init(Cipher.DECRYPT_MODE, privateKey);
    }

    /**
     * base64加密
     *
     * @param param
     * @return
     */
    public static String encodeBase64(String param) {
        byte[] encodeBytes = Base64.getEncoder().encode(param.getBytes());
        return new String(encodeBytes);
    }

    /**
     * base64解密
     *
     * @param param
     * @return
     */
    public static String decodeBase64(String param) {
        byte[] decodeBytes = Base64.getDecoder().decode(param.getBytes());
        return new String(decodeBytes);
    }

    /**
     * md5加密，不能解密
     *
     * @param param
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encodeMd5(String param) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] encodeBytes = md.digest(param.getBytes());
        return Hex.encodeHexString(encodeBytes);
    }

    /**
     * SHA安全散列算法加密，不能解密
     *
     * @param param
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encodeSha(String param) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(param.getBytes());
        return Hex.encodeHexString(md.digest());
    }

    /**
     * aes加密
     *
     * @param param
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     */
    public static String encodeAes(String param) throws BadPaddingException, IllegalBlockSizeException {
        byte[] resultBytes = cipherEncode.doFinal(param.getBytes());
        return Hex.encodeHexString(resultBytes);
    }

    /**
     * aes解密
     *
     * @param param
     * @return
     * @throws DecoderException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static String decodeAes(String param) throws DecoderException, BadPaddingException, IllegalBlockSizeException {
        byte[] result = Hex.decodeHex(param.toCharArray());
        return new String(cipherDecode.doFinal(result));
    }

    /**
     * rsa私钥加密
     *
     * @param param
     * @return
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static String encodeRsaPrivate(String param) throws BadPaddingException, IllegalBlockSizeException {
        byte[] resultBytes = cipherPrivateEncode.doFinal(param.getBytes());
        return Hex.encodeHexString(resultBytes);
    }

    /**
     * rsa公钥加密
     *
     * @param param
     * @return
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static String encodeRsaPublic(String param) throws BadPaddingException, IllegalBlockSizeException {
        byte[] resultBytes = cipherPublicEncode.doFinal(param.getBytes());
        return Hex.encodeHexString(resultBytes);
    }

    /**
     * rsa私钥解密
     *
     * @param param
     * @return
     * @throws DecoderException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static String decodeRsaPrivate(String param) throws DecoderException, BadPaddingException, IllegalBlockSizeException {
        byte[] resultBytes = cipherPrivateDecode.doFinal(Hex.decodeHex(param.toCharArray()));
        return new String(resultBytes);
    }

    /**
     * rsa公钥解密
     *
     * @param param
     * @return
     * @throws DecoderException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static String decodeRsaPublic(String param) throws DecoderException, BadPaddingException, IllegalBlockSizeException {
        byte[] resultBytes = cipherPublicDecode.doFinal(Hex.decodeHex(param.toCharArray()));
        return new String(resultBytes);
    }

    /**
     * main
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            SecuritUtils.init();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        String test = "test123";
        System.out.println("start---------------" + test);

        int index = 0;
        while (index < 100) {
            long base1 = System.currentTimeMillis();
            String base = SecuritUtils.encodeBase64(test);
            long base2 = System.currentTimeMillis();
            System.out.println("base64-" + (base2 - base1) + "--------------" + base);
            long base3 = System.currentTimeMillis();
            base = SecuritUtils.decodeBase64(base);
            long base4 = System.currentTimeMillis();
            System.out.println("base64-" + (base4 - base3) + "--------------" + base);

            try {
                long md51 = System.currentTimeMillis();
                String md5 = SecuritUtils.encodeMd5(test);
                long md52 = System.currentTimeMillis();
                System.out.println("md5-" + (md52 - md51) + "--------------" + md5);
                long md53 = System.currentTimeMillis();
                md5 = SecuritUtils.encodeMd5(test);
                long md54 = System.currentTimeMillis();
                System.out.println("md5-" + (md54 - md53) + "--------------" + md5);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            try {
                long sha1 = System.currentTimeMillis();
                String sha = SecuritUtils.encodeSha(test);
                long sha2 = System.currentTimeMillis();
                System.out.println("sha-" + (sha2 - sha1) + "--------------" + sha);
                long sha3 = System.currentTimeMillis();
                sha = SecuritUtils.encodeSha(test);
                long sha4 = System.currentTimeMillis();
                System.out.println("sha-" + (sha4 - sha3) + "--------------" + sha);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            try {
                long aes1 = System.currentTimeMillis();
                String aes = SecuritUtils.encodeAes(test);
                long aes2 = System.currentTimeMillis();
                System.out.println("aes-" + (aes2 - aes1) + "--------------" + aes);
                long aes3 = System.currentTimeMillis();
                aes = SecuritUtils.decodeAes(aes);
                long aes4 = System.currentTimeMillis();
                System.out.println("aes-" + (aes4 - aes3) + "--------------" + aes);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                long rsa1 = System.currentTimeMillis();
                String rsapu = SecuritUtils.encodeRsaPublic(test);
                long rsa2 = System.currentTimeMillis();
                System.out.println("rsapu-" + (rsa2 - rsa1) + "--------------" + rsapu);
                long rsa3 = System.currentTimeMillis();
                rsapu = SecuritUtils.decodeRsaPrivate(rsapu);
                long rsa4 = System.currentTimeMillis();
                System.out.println("rsapu-" + (rsa4 - rsa3) + "--------------" + rsapu);
                long rsa5 = System.currentTimeMillis();
                String rsapr = SecuritUtils.encodeRsaPrivate(test);
                long rsa6 = System.currentTimeMillis();
                System.out.println("rsapr-" + (rsa6 - rsa5) + "--------------" + rsapr);
                long rsa7 = System.currentTimeMillis();
                rsapr = SecuritUtils.decodeRsaPublic(rsapr);
                long rsa8 = System.currentTimeMillis();
                System.out.println("rsapr-" + (rsa8 - rsa7) + "--------------" + rsapr);
            } catch (Exception e) {
                e.printStackTrace();
            }
            index++;
        }
    }
}
