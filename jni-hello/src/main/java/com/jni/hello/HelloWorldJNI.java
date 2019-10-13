package com.jni.hello;

/**
 * Hello world implementation using native library.
 *
 * @author Yasitha Thilakaratne
 */
public class HelloWorldJNI {

    // Loading C++ library which supports say hello
    static {
        System.loadLibrary("native");
    }

    public static void main(String[] args) {
        new HelloWorldJNI().sayHello();
    }

    /**
     * Say hello using native library
     */
    private native void sayHello();
}
