package com.bahadirakin.cipher;

/**
 * Created by bhdrkn on 21/12/14.
 * <p/>
 * For more information please check
 * http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Cipher
 */
public enum CipherMode {
    CBC("CBC"),
    CFB("CFB"),
    CTR("CTR");

    private final String mode;

    private CipherMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
