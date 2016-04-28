//
// Created by idea on 16/4/27.
//
#include "com_example_idea_androiddemopartone_act_JniTestTwoActivity.h"

JNIEXPORT jstring JNICALL Java_com_example_idea_androiddemopartone_act_JniTestTwoActivity_getStringFromNative
        (JNIEnv * env, jobject obj) {
    return (*env)->NewStringUTF(env,"my second jni demo!");
}

