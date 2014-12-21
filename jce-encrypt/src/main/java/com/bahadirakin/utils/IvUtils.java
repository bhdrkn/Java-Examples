package com.bahadirakin.utils;

import javax.crypto.spec.IvParameterSpec;

/**
 * Created by bhdrkn on 21/12/14.
 */
public final class IvUtils {

    public static final IvParameterSpec generateDefaultVector() {
        // build the initialization vector.  All zeros will be used as default
        byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        return IvUtils.generateVector(iv);
    }

    public static final IvParameterSpec generateVector(final byte[] bytes) {
        return new IvParameterSpec(bytes);
    }
}
