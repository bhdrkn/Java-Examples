package com.bahadirakin.cipher;

import com.bahadirakin.utils.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

/**
 * Created by bhdrkn on 21/12/14.
 */
@RunWith(JUnit4.class)
public class AESTest {

    @Test
    public void testAES() throws Exception {
        final String input = "Hello, World!";

        final Key key = KeyUtils.generateAESKey();
        Assert.assertNotNull(key);

        final ICipher iCipher = SymetricCipherProvider.createCipherInstance(CipherAlgorithm.AES, key);
        Assert.assertNotNull(iCipher);

        final byte[] encrpyted = iCipher.encrypt(input.getBytes("UTF-8"));
        Assert.assertNotNull(encrpyted);

        final byte[] decrypted = iCipher.decrypt(encrpyted);
        Assert.assertNotNull(decrypted);

        final String inputVer2= new String(decrypted,"UTF-8");
        Assert.assertEquals(input, inputVer2);
    }

    @Test
    public void testDES() throws Exception {
        final String input = "Hello, World!";

        final Key key = KeyUtils.generateDESKey();
        Assert.assertNotNull(key);

        final ICipher iCipher = SymetricCipherProvider.createCipherInstance(CipherAlgorithm.DES, key);
        Assert.assertNotNull(iCipher);

        final byte[] encrpyted = iCipher.encrypt(input.getBytes("UTF-8"));
        Assert.assertNotNull(encrpyted);

        final byte[] decrypted = iCipher.decrypt(encrpyted);
        Assert.assertNotNull(decrypted);

        final String inputVer2= new String(decrypted,"UTF-8");
        Assert.assertEquals(input, inputVer2);
    }

    @Test
    public void testAESwithIV() throws Exception {
        final String input = "Hello, World!";

        final Key key = KeyUtils.generateAESKey();
        Assert.assertNotNull(key);

        final IvParameterSpec ivParameterSpec = IvUtils.generateDefaultVector();
        Assert.assertNotNull(ivParameterSpec);

        final ICipher iCipher = SymetricCipherProvider.createCipherInstance(CipherAlgorithm.AES, CipherMode.CBC, CipherPadding.PKCS5Padding, key, ivParameterSpec);
        Assert.assertNotNull(iCipher);

        final byte[] encrpyted = iCipher.encrypt(input.getBytes("UTF-8"));
        Assert.assertNotNull(encrpyted);

        final byte[] decrypted = iCipher.decrypt(encrpyted);
        Assert.assertNotNull(decrypted);

        final String inputVer2= new String(decrypted,"UTF-8");
        Assert.assertEquals(input, inputVer2);
    }

    @Test
    public void testAESWithLongKeys() throws Exception{
        final String input = "Hello, World!";
        final String keyStr = "abcdABCD1234abcdABCD1234abcdABCD";

        final Key key = KeyUtils.createAESKey(keyStr);
        Assert.assertNotNull(key);

        final IvParameterSpec ivParameterSpec = IvUtils.generateDefaultVector();
        Assert.assertNotNull(ivParameterSpec);

        final ICipher iCipher = SymetricCipherProvider.createCipherInstance(CipherAlgorithm.AES, CipherMode.CBC, CipherPadding.PKCS5Padding, key, ivParameterSpec);
        Assert.assertNotNull(iCipher);

        final byte[] encrpyted = iCipher.encrypt(input.getBytes("UTF-8"));
        Assert.assertNotNull(encrpyted);

        final byte[] decrypted = iCipher.decrypt(encrpyted);
        Assert.assertNotNull(decrypted);

        final String inputVer2= new String(decrypted,"UTF-8");
        Assert.assertEquals(input, inputVer2);
    }
}
