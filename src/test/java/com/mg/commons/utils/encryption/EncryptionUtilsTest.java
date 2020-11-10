package com.mg.commons.utils.encryption;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.security.KeyPair;

public class EncryptionUtilsTest {

    @Test
    public void testMD5() {
        String expect = "202CB962AC59075B964B07152D234B70";
        String result = EncryptionUtils.getMD5String("123");
        Assert.assertTrue(expect.equals(result));
    }

    @Test
    public void testMD5Bytes() {
        String expect = "202CB962AC59075B964B07152D234B70";
        String result = EncryptionUtils.getMD5String("123".getBytes());
        Assert.assertTrue(expect.equals(result));
    }

    @Test
    public void testGetFileMD5() {
        String expect = "202CB962AC59075B964B07152D234B70";
        File file = new File("src/test/resources/md5.txt");
        String result = EncryptionUtils.md5File(file);
        Assert.assertTrue(expect.equals(result));
    }

    @Test
    public void testSha256() {
        String expect = "A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3";
        String result = EncryptionUtils.sha256String("123");
        Assert.assertTrue(expect.equals(result));
    }

    @Test
    public void testSha256File() {
        String expect = "A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3";
        File file = new File("src/test/resources/md5.txt");
        String result = EncryptionUtils.sha256File(file);
        Assert.assertTrue(expect.equals(result));
    }

    @Test
    public void testEncodeAES() throws Exception {
        String expect = "ZRdpVOakD6jPUxCXsjDPwg==";
        String result = EncryptionUtils.encodeByAES("123", "!@#$");
        Assert.assertTrue(expect.equals(result));
    }

    @Test
    public void testDecodeAES() throws Exception {
        String expect = "123";
        String result = EncryptionUtils.decodeByAES("ZRdpVOakD6jPUxCXsjDPwg==", "!@#$");
        Assert.assertTrue(expect.equals(result));
    }

    @Test
    public void testRsa() throws Exception {
        KeyPair keyPair = EncryptionUtils.getKeyPair();
        String publicKey = EncryptionUtils.getPublicKey(keyPair);
        String privateKey = EncryptionUtils.getPrivateKey(keyPair);
        String bytes = EncryptionUtils.encryptByRSA("123", publicKey);
        String result = EncryptionUtils.decryptByRSA(bytes, privateKey);
        Assert.assertTrue("123".equals(result));
    }

}