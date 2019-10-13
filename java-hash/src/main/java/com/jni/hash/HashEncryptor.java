package com.jni.hash;

import com.jni.commons.DLLLoader;

/**
 * A hashing implementation which hashes given string
 * using native library.
 *
 * @author Yasitha Thilakaratne
 */
public class HashEncryptor {

    // Loading C library which supports MD5 hashing in native
    // Internally calls java.lang.System.loadLibrary(String)
    static {
        DLLLoader.loadLibrary("native-hash");
    }

    /**
     * Calculates the MD5 hash code for the given string.
     *
     * @param string string to hash
     * @return MD5 hash string
     */
    public native String md5(String string);
}
