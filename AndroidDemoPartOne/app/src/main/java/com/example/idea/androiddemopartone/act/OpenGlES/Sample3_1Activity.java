package com.example.idea.androiddemopartone.act.OpenGlES;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.idea.androiddemopartone.R;
import com.example.idea.androiddemopartone.view.OpenGlES.MyTDView;

/**
 * Created by idea on 16/8/9.
 */
public class Sample3_1Activity extends Activity {
    MyTDView mview;
    private RelativeLayout rl_container;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //设置为竖屏模式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mview=new MyTDView(this);
        mview.requestFocus();
        mview.setFocusableInTouchMode(true);
        setContentView(R.layout.act_sample_3_1);
        rl_container=(RelativeLayout) findViewById(R.id.rl_container);
        rl_container.addView(mview);
        TextView t=new TextView(this);
        t.setText("hhhh");
        t.setTextColor(Color.WHITE);
        rl_container.addView(t);

    }
    @Override
    public void onResume()
    {
        super.onResume();
        mview.onResume();
    }
    @Override
    public void onPause()
    {
        super.onPause();
        mview.onPause();
    }

}
