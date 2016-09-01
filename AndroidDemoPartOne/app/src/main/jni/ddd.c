//
// Created by idea on 16/4/27.
//
#include "com_example_idea_androiddemopartone_act_JniTestTwoActivity.h"
#include <android/log.h> // 引入log.h的头文件. 声明了打印的方法和优先级
#define LOG_TAG "ddd" // 定义一个常量LOG_TAG, 值为: cTag
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)


JNIEXPORT jstring JNICALL Java_com_example_idea_androiddemopartone_act_JniTestTwoActivity_getStringFromNative
        (JNIEnv * env, jobject obj) {

    return (*env)->NewStringUTF(env,"my second jni demo!");
}

