#include "Cache.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <iostream>
#include <map>

using namespace std;
#ifdef __cplusplus
extern "C" {
#endif

 map<long, jobject> javaCache;

 /* Adds a java object to the cache */
 JNIEXPORT void JNICALL Java_com_jni_cache_Cache_add(JNIEnv *env, jobject caller, jobject obj, jlong id)
 {
        jobject globalObj = env->NewGlobalRef(obj);
        javaCache.insert({ id, globalObj});
        cout<<"CPP log: Entity for key ["<<id<<"] added to the cache."<<endl;
 }

 /* Retrieves a java object from cache */
 JNIEXPORT jobject JNICALL Java_com_jni_cache_Cache_get(JNIEnv *env, jobject caller, jlong id)
 {
        cout<<"CPP log: Retrieving id ["<<id<<"] from the cache."<<endl;

        jobject obj = javaCache.find(id)->second;

        if (env->IsSameObject(obj, NULL)) {
            cout<<"CPP log: Cached value for id ["<<id<<"] is not found."<<endl;
        }

        return obj;
 }

 /* Remove object from cache and deallocates the memory */
 JNIEXPORT void JNICALL Java_com_jni_cache_Cache_delete(JNIEnv *env, jobject caller, jlong id)
 {
        jobject obj = javaCache.find(id)->second;
        javaCache.erase(id);
        env->DeleteGlobalRef(obj);
        cout<<"CPP log: ["<<id<<"] removed from the cache."<<endl;
 }

#ifdef __cplusplus
}
#endif