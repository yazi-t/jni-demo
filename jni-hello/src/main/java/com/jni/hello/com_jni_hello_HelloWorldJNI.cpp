#include <iostream>
#include <jni.h>        // JNI header provided by JDK
#include <stdio.h>      // C Standard IO Header
#include "com_jni_hello_HelloWorldJNI.h"   // Generated

JNIEXPORT void JNICALL Java_com_jni_hello_HelloWorldJNI_sayHello(JNIEnv* env, jobject thisObject) {
    std::cout << "Hello from C++ !!" << std::endl;
}