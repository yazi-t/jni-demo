#include <jni.h>    // JNI header provided by JDK
#include<stdio.h>
#include <string.h>
#include "MD5.h"    // header file for the GPL licenced MD5.C implementation

 /* JNI called C method which hashes the given string */
 JNIEXPORT jstring JNICALL Java_com_jni_hash_HashEncryptor_md5(JNIEnv * env, jobject obj, jstring javaString)
 {
        size_t len = 0;
        const char *nativeString = (*env)->GetStringUTFChars(env, javaString, 0);
        struct md5_ctx ctx;
        unsigned char digest[16];
        md5_init(&ctx);
        ctx.size = len?len:strlen(nativeString);
        strcpy(ctx.buf, nativeString);
        md5_update(&ctx);
        md5_final(digest, &ctx);
        return(*env)->NewStringUTF(env,digest);
 }