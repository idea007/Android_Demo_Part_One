package com.example.idea.androiddemopartone.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.idea.androiddemopartone.R;
import com.example.idea.androiddemopartone.view.LoopViewPager.LoopViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoopViewPagerActivity extends AppCompatActivity{


    @Bind(R.id.lvp_loop)
    LoopViewPager lvp_loop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_loopviewpager);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {


    }


}
