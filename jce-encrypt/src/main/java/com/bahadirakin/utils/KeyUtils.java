package com.bahadirakin.utils;

import com.bahadirakin.cipher.CipherAlgorithm;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * Created by bhdrkn on 21/12/14.
 */
public final class KeyUtils {

    public static final Key generateDESKey() {
        try {
            return KeyUtils.generateKey(CipherAlgorithm.DES);
        } catch (NoSuchAlgorithmException e) {
            // DES'in herzaman oldugunu biliyoruz
            return null;
        }
    }

    public static final Key createDESKey(final byte[] key) {
        return KeyUtils.createKey(key, CipherAlgorithm.DES);
    }

    public static final Key createDESKey(final String key) {
        return KeyUtils.createKey(key, CipherAlgorithm.DES);
    }

    public static final Key generateAESKey() {
        try {
            return KeyUtils.generateKey(CipherAlgorithm.AES);
        } catch (NoSuchAlgorithmException e) {
            // AES'in herzaman oldugunu biliyoruz
            return null;
        }
    }

    public static final Key createAESKey(final byte[] key) {
        return KeyUtils.createKey(key, CipherAlgorithm.AES);
    }

    public static final Key createAESKey(final String key) {
        return KeyUtils.createKey(key, CipherAlgorithm.AES);
    }

    public static final String parseAsBase64(final Key key) {
        return Base64.encodeBytes(key.getEncoded());
    }

    public static final Key createKey(final byte[] key, final CipherAlgorithm cipherAlgorithm) {
        return new SecretKeySpec(key, cipherAlgorithm.getAlgorithm());
    }

    public static final Key createKey(final String key, final CipherAlgorithm cipherAlgorithm) {
        try {
            return new SecretKeySpec(key.getBytes("UTF-8"), cipherAlgorithm.getAlgorithm());
        } catch (UnsupportedEncodingException e) {
            // UTF-8'in bulunmamasi pek olası degil
            return null;
        }

    }

    public static final Key generateKey(final CipherAlgorithm cipherAlgorithm) throws NoSuchAlgorithmException {
        final KeyGenerator keyGenerator = KeyGenerator.getInstance(cipherAlgorithm.getAlgorithm());
        return keyGenerator.generateKey();
    }
}
