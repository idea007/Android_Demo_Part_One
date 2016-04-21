package com.example.idea.androiddemopartone.utils;

import android.app.Activity;
import android.content.Intent;

import com.example.idea.androiddemopartone.act.InteractiveActivity;
import com.example.idea.androiddemopartone.act.JniTestActivity;
import com.example.idea.androiddemopartone.act.NonInteractiveActivity;
import com.example.idea.androiddemopartone.act.ProgressBarActivity;

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

    public static void toNoninteractiveAct(Activity act, boolean isFinish){
        act.startActivity(new Intent(act, NonInteractiveActivity.class));
        if(isFinish){
            act.finish();
        }
    }

    public static void toInteractiveAct(Activity act, boolean isFinish){
        act.startActivity(new Intent(act, InteractiveActivity.class));
        if(isFinish){
            act.finish();
        }
    }

    public static void toProgressBarAct(Activity act, boolean isFinish){
        act.startActivity(new Intent(act, ProgressBarActivity.class));
        if(isFinish){
            act.finish();
        }
    }
}
