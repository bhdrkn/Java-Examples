package com.bahadirakin.cipher;

/**
 * Created by bhdrkn on 21/12/14.
 */
public class CipherException extends Exception {
    public CipherException() {
    }

    public CipherException(String message) {
        super(message);
    }

    public CipherException(String message, Throwable cause) {
        super(message, cause);
    }

    public CipherException(Exception e) {
        super(e);
    }
}
