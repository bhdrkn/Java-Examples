package com.bahadirakin.cipher;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;

/**
 * Created by bhdrkn on 21/12/14.
 */
public class SymetricCipher implements ICipher {
    private final Cipher cipher;
    private final Key key;
    private final IvParameterSpec initializationVector;


    public SymetricCipher(Cipher cipher, Key key, IvParameterSpec initializationVector) {
        this.cipher = cipher;
        this.key = key;
        this.initializationVector = initializationVector;
    }

    public SymetricCipher(Cipher cipher, Key key) {
        this(cipher, key, null);
    }

    @Override
    public byte[] encrypt(byte[] input) throws CipherException {
        initCipher(Cipher.ENCRYPT_MODE);
        try {
            return this.cipher.doFinal(input);
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    @Override
    public byte[] decrypt(byte[] input) throws CipherException {
        initCipher(Cipher.DECRYPT_MODE);
        try {
            return this.cipher.doFinal(input);
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    private void initCipher(final int opcode) {
        try {
            if (initializationVector == null) {
                cipher.init(opcode, key);
            } else {
                cipher.init(opcode, key, initializationVector);
            }
        } catch (Exception e) {
            throw new RuntimeException("Cipher initialization exception", e);
        }

    }
}
