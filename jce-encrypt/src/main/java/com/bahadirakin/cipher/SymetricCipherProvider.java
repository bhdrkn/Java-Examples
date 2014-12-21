package com.bahadirakin.cipher;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

/**
 * Created by bhdrkn on 21/12/14.
 */
public final class SymetricCipherProvider {

    private static final String FULL_ALGORITHM_FORMAT = "%s/%s/%s";
    private static final String SIMPLE_ALGORITHM_FORMAT = "%s";

    public static ICipher createCipherInstance(final CipherAlgorithm cipherAlgorithm, final CipherMode cipherMode, final CipherPadding cipherPadding, final Key key, final IvParameterSpec ivParameterSpec) throws CipherException {
        try {
            final Cipher cipher = Cipher.getInstance(SymetricCipherProvider.createAlgorithm(cipherAlgorithm, cipherMode, cipherPadding));
            if (cipherMode == CipherMode.CBC && ivParameterSpec == null) {
                throw new CipherException("CBC Mode requires initialization vector");
            }

            final ICipher iCipher = new SymetricCipher(cipher, key, ivParameterSpec);
            return iCipher;
        } catch (Exception e) {
            throw new CipherException(e);
        }

    }

    public static ICipher createCipherInstance(final CipherAlgorithm cipherAlgorithm, final Key key) throws CipherException {
        try {
            final Cipher cipher = Cipher.getInstance(SymetricCipherProvider.createAlgorithm(cipherAlgorithm));
            final ICipher iCipher = new SymetricCipher(cipher, key);
            return iCipher;
        } catch (Exception e) {
            throw new CipherException(e);
        }

    }

    public static final String createAlgorithm(final CipherAlgorithm cipherAlgorithm, final CipherMode cipherMode, final CipherPadding cipherPadding) {
        if (cipherMode == null || cipherPadding == null) {
            return String.format(SIMPLE_ALGORITHM_FORMAT, cipherAlgorithm.getAlgorithm());
        } else {
            return String.format(FULL_ALGORITHM_FORMAT, cipherAlgorithm.getAlgorithm(), cipherMode.getMode(), cipherPadding.getPadding());
        }
    }

    public static final String createAlgorithm(final CipherAlgorithm cipherAlgorithm) {
        return SymetricCipherProvider.createAlgorithm(cipherAlgorithm, null, null);
    }
}
