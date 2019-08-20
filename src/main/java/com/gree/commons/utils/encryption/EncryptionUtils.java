package com.gree.commons.utils.encryption;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;

/**
 * @author iceWang 260259@gree.com.cn
 * @date Create in 15:23 2019/6/12
 * @description
 */
public class EncryptionUtils {

    public static final String AES = "AES";
    public static final String AES_CIPHER_MODE = "AES/ECB/PKCS5Padding";
    public static final String RSA = "RSA";
    public static final String SHA256 = "SHA-256";
    public static final String MD5 = "MD5";

    public static final char[] HEX_DIGEST = "0123456789ABCDEF".toCharArray();

    public static final String UTF = "utf-8";

    static MessageDigest md5Digest;
    static MessageDigest sha256Digest;

    static {
        try {
            md5Digest = MessageDigest.getInstance(MD5);
            sha256Digest = MessageDigest.getInstance(SHA256);
        } catch (NoSuchAlgorithmException e) {

        }
    }

    /**
     * AES 加密，默认使用 AES/ECB/PKCS5Padding 算法
     * @param content
     * @param key
     * @return
     * @throws Exception
     */
    public static String encodeByAES(String content, String key) throws Exception {
        return encodeByAES(content, key, AES_CIPHER_MODE);
    }

    public static String encodeByAES(String content, String key, String algorithm) throws Exception {
        //1.构造密钥生成器，指定为AES算法,不区分大小写
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        //2.根据encodeRules规则初始化密钥生成器，这里的数字可以是128,192,256，数字越大越安全。
        keyGenerator.init(128, new SecureRandom(key.getBytes(UTF)));
        //3.生成AES密钥
        SecretKeySpec keySpec = new SecretKeySpec(keyGenerator.generateKey().getEncoded(), AES);
        //4.根据指定算法AES自成密码器
        Cipher cipher = Cipher.getInstance(algorithm);
        //5.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        //6.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
        byte[] bytesAes = cipher.doFinal(content.getBytes(UTF));
        return Base64.getEncoder().encodeToString(bytesAes);
    }

    /**
     * AES 解密，默认使用 AES/ECB/PKCS5Padding 算法
     * @param content
     * @param key
     * @return
     * @throws Exception
     */
    public static String decodeByAES(String content, String key) throws Exception {
        return decodeByAES(content, key, AES_CIPHER_MODE);
    }

    public static String decodeByAES(String content, String key, String algorithm) throws Exception {
        //1.构造密钥生成器，指定为AES算法,不区分大小写
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        //2.根据encodeRules规则初始化密钥生成器，这里的数字可以是128、192、256，数字越大越安全。
        keyGenerator.init(128, new SecureRandom(key.getBytes(UTF)));
        //3.生成AES密钥
        SecretKeySpec keySpec = new SecretKeySpec(keyGenerator.generateKey().getEncoded(), AES);
        //4.根据指定算法AES自成密码器
        Cipher cipher = Cipher.getInstance(algorithm);
        //5.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        //6.将加密并编码后的内容解码成字节数组
        byte[] byteContent = Base64.getDecoder().decode(content);
        byte[] decryptBytes = cipher.doFinal(byteContent);
        return new String(decryptBytes, UTF);
    }


    // -----------------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------

    /**
     * 生成公钥和私钥对
     *
     * @return
     * @throws Exception
     */
    public static KeyPair getKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
            // 可以是1024，2048等
            keyPairGenerator.initialize(1024);
            // 获得密钥对
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            return keyPair;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getPublicKey(KeyPair keyPair) {
        PublicKey keyPairPublic = keyPair.getPublic();
        return Base64.getEncoder().encodeToString(keyPairPublic.getEncoded());
    }

    public static String getPrivateKey(KeyPair keyPair) {
        PrivateKey keyPairPrivate = keyPair.getPrivate();
        return Base64.getEncoder().encodeToString(keyPairPrivate.getEncoded());
    }

    /**
     * 使用公钥对内容进行加密
     *
     * @param content
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static String encryptByRSA(String content, String publicKey) throws Exception {
        byte[] publicBytes = Base64.getDecoder().decode(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(RSA).generatePublic(new X509EncodedKeySpec(publicBytes));
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(content.getBytes(UTF)));
    }

    /**
     * 使用私钥对内容进行解密
     *
     * @param content
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String decryptByRSA(String content, String privateKey) throws Exception {
        byte[] privateBytes = Base64.getDecoder().decode(privateKey.getBytes(UTF));
        byte[] contentByte = Base64.getDecoder().decode(content.getBytes(UTF));
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(RSA).generatePrivate(new PKCS8EncodedKeySpec(privateBytes));
        //RSA解密
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(contentByte), UTF);
    }


    // -----------------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------

    /**
     * 返回字符串的 MD5
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static String getMD5String(String context) {
        byte[] digest = new byte[0];
        try {
            digest = md5Digest.digest(context.getBytes(UTF));
        } catch (UnsupportedEncodingException e) {
        }
        return toHex(digest);
    }

    public static String getMD5String(byte[] bytes) {
        byte[] digest = md5Digest.digest(bytes);
        return toHex(digest);
    }


    /**
     * 获取文件的 MD5
     *
     * @param file
     * @return
     */
    public static String md5File(File file) {
        byte[] digest = getBytes(file, MD5);
        if (Objects.isNull(digest)) {
            return null;
        }
        return toHex(digest);
    }

    // --------------------------------------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------------------------------------

    public static String sha256String(String context) {
        byte[] digest = new byte[0];
        try {
            digest = sha256Digest.digest(context.getBytes(UTF));
        } catch (UnsupportedEncodingException e) {
        }
        return toHex(digest);
    }

    public static String sha256File(File file) {
        byte[] digest = getBytes(file, SHA256);
        if (Objects.isNull(digest)) {
            return null;
        }
        return toHex(digest);
    }

    /**
     * 通过传入不同的加密算法实现文件读入
     *
     * @param file
     * @param algorithm
     * @return
     */
    private static byte[] getBytes(File file, String algorithm) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            MessageDigest messageDigest = null;
            switch (algorithm) {
                case MD5:
                    messageDigest = md5Digest;
                    break;
                case SHA256:
                    messageDigest = sha256Digest;
                    break;
                default:
                    throw new Exception("请输入正确的加密算法");
            }
            int length = 0;
            byte[] buffer = new byte[8192];
            while ((length = fileInputStream.read(buffer)) != -1) {
                messageDigest.update(buffer, 0, length);
            }
            return messageDigest.digest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将加密后字节数组变为16进制字符序列
     *
     * @param bytes
     * @return
     */
    private static String toHex(byte[] bytes) {
        if (Objects.isNull(bytes)) {
            return "";
        }
        StringBuilder builder = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            builder.append(HEX_DIGEST[(bytes[i] >> 4) & 0x0f]);
            builder.append(HEX_DIGEST[bytes[i] & 0x0f]);
        }
        return builder.toString();
    }

}
