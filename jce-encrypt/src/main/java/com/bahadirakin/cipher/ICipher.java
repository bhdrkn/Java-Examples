package com.bahadirakin.cipher;

/**
 * Created by bhdrkn on 21/12/14.
 */
public interface ICipher {

    public byte[] encrypt(final byte[] input) throws CipherException;

    public byte[] decrypt(final byte[] input) throws CipherException;
}
