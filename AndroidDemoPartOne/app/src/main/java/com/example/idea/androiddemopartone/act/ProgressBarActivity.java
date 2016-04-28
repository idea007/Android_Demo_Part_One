package com.example.idea.androiddemopartone.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.idea.androiddemopartone.R;
import com.example.idea.androiddemopartone.view.CircleSeekBar;
import com.example.idea.androiddemopartone.view.ThreeDProgressbar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProgressBarActivity extends AppCompatActivity{

    @Bind(R.id.tdp_progress)
    ThreeDProgressbar tdp_progress;

    @Bind(R.id.csb_volumebar)
    CircleSeekBar csb_volumebar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_progressbar);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        tdp_progress.setMax(100);
        tdp_progress.setProgress(50);

        csb_volumebar.setMax(150);
        csb_volumebar.setProgressBarColor(R.color.colorPrimary);
        csb_volumebar.setProgress(149);
    }

}
