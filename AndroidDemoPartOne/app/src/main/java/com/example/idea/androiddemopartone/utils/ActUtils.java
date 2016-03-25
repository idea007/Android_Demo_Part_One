package com.example.idea.androiddemopartone.utils;

import android.app.Activity;
import android.content.Intent;

import com.example.idea.androiddemopartone.act.JniTestActivity;

/**
 * Created by idea on 16/3/25.
 */
public class ActUtils {
    public static void toJniTestAct(Activity act, boolean isFinish){
        act.startActivity(new Intent(act, JniTestActivity.class));
        if(isFinish){
            act.finish();
        }
    }
}
