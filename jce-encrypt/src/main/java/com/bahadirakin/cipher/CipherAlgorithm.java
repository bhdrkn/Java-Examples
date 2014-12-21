package com.bahadirakin.cipher;

/**
 * Created by bhdrkn on 21/12/14.
 * <p/>
 * For more information please check
 * http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Cipher
 */
public enum CipherAlgorithm {
    AES("AES"),
    DES("DES");

    private final String algorithm;

    private CipherAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }
}