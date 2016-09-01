package com.example.idea.androiddemopartone.OpenGlES.glesAct;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.idea.androiddemopartone.OpenGlES.glesView.MySurfaceView;
import com.example.idea.androiddemopartone.R;

import butterknife.Bind;

/**
 * Created by idea on 16/8/9.
 */
public class Sample5_1_Activity extends Activity {
    @Bind(R.id.msv)
    MySurfaceView mGLSurfaceView;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //设置为竖屏模式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        //初始化GLSurfaceView
        mGLSurfaceView = new MySurfaceView(this);

        //切换到主界面
        setContentView(mGLSurfaceView);


//        setContentView(R.layout.act_sample_5_1);
//        ButterKnife.bind(this);
        mGLSurfaceView.requestFocus();
        mGLSurfaceView.setFocusableInTouchMode(true);//设置为可触控


    }

    @SuppressLint("NewApi") @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @SuppressLint("NewApi") @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }

}
