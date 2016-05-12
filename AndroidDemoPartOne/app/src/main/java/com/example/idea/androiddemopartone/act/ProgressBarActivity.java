package com.example.idea.androiddemopartone.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.idea.androiddemopartone.R;
import com.example.idea.androiddemopartone.view.CircleSeekBar;
import com.example.idea.androiddemopartone.view.ClockProgressDrawable;
import com.example.idea.androiddemopartone.view.ClockProgressView;
import com.example.idea.androiddemopartone.view.SymmetrySeekBar;
import com.example.idea.androiddemopartone.view.ThreeDProgressbar;
import com.example.idea.androiddemopartone.view.rangebar.RangeBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProgressBarActivity extends AppCompatActivity{

    @Bind(R.id.tdp_progress)
    ThreeDProgressbar tdp_progress;

    @Bind(R.id.csb_volumebar)
    CircleSeekBar csb_volumebar;

    @Bind(R.id.ssb_symmetry)
    SymmetrySeekBar ssb_symmetry;

    @Bind(R.id.iv_progress)
    ImageView iv_progress;

    @Bind(R.id.cpv_clock)
    ClockProgressView cpv_clock;

    @Bind(R.id.sb_seekbar)
    SeekBar sb_seekbar;

    @Bind(R.id.rb_rangebar)
    RangeBar rangebar;

    private ClockProgressDrawable arrowDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_progressbar);
        ButterKnife.bind(this);

        initView();

        initClockView();

        initRangeBar();


    }

    private void initRangeBar() {
//        rangebar.setBarColor(getResources().getColor(R.color.ldrawer_color));
        rangebar.setConnectingLineColor(getResources().getColor(R.color.ldrawer_color));
        rangebar.setTickEnd(100);


        rangebar.setConnectingLineWeight(3);
        rangebar.setBarWeight(12);
        rangebar.setBarColor(getResources().getColor(R.color.ldrawer_color));

//        rangebar.setBackgroundColor(getResources().getColor(R.color.ldrawer_color));
        rangebar.setDrawingCacheBackgroundColor(getResources().getColor(R.color.ldrawer_color));
        rangebar.setSelectorColor(getResources().getColor(R.color.ldrawer_color));
        rangebar.setTickColor(getResources().getColor(R.color.ldrawer_color));



        rangebar.setRangePinsByIndices(0, 0);
        rangebar.setRangeBarEnabled(!rangebar.isRangeBar());


//        rangebar.setTickColor(getResources().getColor(R.color.ldrawer_color));


//        rangebar.setPinColor(getResources().getColor(R.color.ldrawer_color));


//        rangebar.setRangePinsByIndices(0, 0);
//        rangebar.setRangeBarEnabled(false);

    }

    private void initClockView() {

//        arrowDrawable = new ClockProgressDrawable(this) {
//
//            @Override
//            public boolean isLayoutRtl() {
//                return false;
//            }
//        };
//
//        iv_progress.setImageDrawable(arrowDrawable);


        sb_seekbar.setMax(180);
        sb_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                float value=(float)progress/100;
//                arrowDrawable.setProgress(value);

                cpv_clock.setProgress(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    private void initView() {
        tdp_progress.setMax(100);
        tdp_progress.setProgress(50);



        csb_volumebar.setMax(150);
        csb_volumebar.setProgressBarColor(R.color.colorPrimary);
        csb_volumebar.setProgress(149);

        ssb_symmetry.setMax(100);
        ssb_symmetry.setProgress(50);
    }

}
