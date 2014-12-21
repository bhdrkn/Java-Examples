package com.bahadirakin.cipher;

/**
 * Created by bhdrkn on 21/12/14.
 * <p/>
 * For more information please check
 * http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Cipher
 */
public enum CipherPadding {
    NoPadding("NoPadding"),
    ISO10126Padding("ISO10126Padding"),
    PKCS5Padding("PKCS5Padding");

    private final String padding;

    private CipherPadding(String padding) {
        this.padding = padding;
    }

    public String getPadding() {
        return padding;
    }
}
