package com.bahadirakin.main;

import com.bahadirakin.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;

/**
 * Created by bhdrkn on 21/12/14.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        final String input = "Hello, World!";
        encryptAndDecryptAES(input);
        encryptAndDecryptDES(input);

    }

    private static void encryptAndDecryptAES(String text) throws Exception {
        final KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        final Key key = keyGenerator.generateKey();

        final Cipher cipher = Cipher.getInstance("AES");
        System.out.println("Max Allowed Key Length: " + Cipher.getMaxAllowedKeyLength("AES"));

        cipher.init(Cipher.ENCRYPT_MODE, key);
        final byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
        System.out.println("Encrypted as Bytes: " + encrypted);
        System.out.println("Encrypted as String: " + Base64.encodeBytes(encrypted));


        cipher.init(Cipher.DECRYPT_MODE, key);
        final byte[] decrypted = cipher.doFinal(encrypted);
        System.out.println("Decrypted as Bytes: " + decrypted);
        System.out.println("Decrypted as String: " + Base64.encodeBytes(decrypted));
    }

    private static void encryptAndDecryptDES(String text) throws Exception {
        final KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        final Key key = keyGenerator.generateKey();

        final Cipher cipher = Cipher.getInstance("DES");
        System.out.println("Max Allowed Key Length: " + Cipher.getMaxAllowedKeyLength("DES"));

        cipher.init(Cipher.ENCRYPT_MODE, key);
        final byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
        System.out.println("Encrypted as Bytes: " + encrypted);
        System.out.println("Encrypted as String: " + Base64.encodeBytes(encrypted));


        cipher.init(Cipher.DECRYPT_MODE, key);
        final byte[] decrypted = cipher.doFinal(encrypted);
        System.out.println("Decrypted as Bytes: " + decrypted);
        System.out.println("Decrypted as String: " + Base64.encodeBytes(decrypted));
    }
}
