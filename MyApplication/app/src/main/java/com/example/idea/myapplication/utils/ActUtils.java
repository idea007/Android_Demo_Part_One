package com.example.idea.myapplication.utils;

import android.app.Activity;
import android.content.Intent;

import com.example.idea.myapplication.VideoPlayerActivity;

/**
 * Created by idea on 16/4/13.
 */
public class ActUtils {
    public static void toVideoPlayerAct(Activity act, boolean isFinish){
        act.startActivity(new Intent(act, VideoPlayerActivity.class));
        if(isFinish){
            act.finish();
        }
    }

}
