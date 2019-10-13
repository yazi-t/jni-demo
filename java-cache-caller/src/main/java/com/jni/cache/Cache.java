package com.jni.cache;

import com.jni.commons.DLLLoader;

/**
 * This class implements a cache which stores java entities in
 * native environment other than the Java heap.
 *
 * @param <T> type of the objects stored in the cache
 *
 * @author Yasitha Thilakaratne
 */
public class Cache<T> {

    // Loading C++ library which supports storing java objects in native allocated memory.
    // Internally calls java.lang.System.loadLibrary(String)
    static {
        DLLLoader.loadLibrary("native-cache");
    }

    /**
     * Adds the object to the native memory cache.
     *
     * @param value object to cache
     * @param key   cache key
     */
    public native void add(T value, long key);

    /**
     * Gets the value cached by key.
     *
     * @param key cache key
     * @return cached value
     */
    public native T get(long key);

    /**
     * Remove object from the cache and releases the native memory.
     *
     * @param key cache key
     */
    public native void delete(long key);
}
