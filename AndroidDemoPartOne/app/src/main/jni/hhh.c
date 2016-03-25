#include "com_example_idea_androiddemopartone_act_JniTestActivity.h"
//
// Created by idea on 16/3/25.
//

JNIEXPORT jstring JNICALL Java_com_example_idea_androiddemopartone_act_JniTestActivity_getStringFromNative_1second
        (JNIEnv *env, jobject obj){
    return (*env)->NewStringUTF(env,"你好");
}