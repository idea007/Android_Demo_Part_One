package com.example.idea.androiddemopartone.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.idea.androiddemopartone.R;

/**
 * Created by idea on 16/4/21.
 */
public class CircleSeekBar extends LinearLayout
{
    private Context mContext;
    private int mPattern;

    private CircleSeekNumberBar mCircleSeekNumberBarLeft;
    private CircleSeekNumberBar mCircleSeekNumberBarRight;

    // 当前所处的模式是2D还是3D-2表示2D-3表示3D
    public static final int D2_Pattern = 2;
    public static final int D3_Pattern = 3;

    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT, 1);

    private int progressColor = Color.YELLOW;

    public CircleSeekBar(Context context)
    {
        super(context);
        init(context, null, 0);
    }

    public CircleSeekBar(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);

        init(context, attrs, defStyle);
    }

    public CircleSeekBar(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mPattern = D3_Pattern;

        init(context, attrs, R.attr.seekArcStyle);

        setProgressBarColor(progressColor);

    }

    private void init(Context context, AttributeSet attrs, int defStyle)
    {
        this.mContext = context;

        if (attrs != null)
        {
            TypedArray typedArray = context.obtainStyledAttributes(attrs,
                    R.styleable.SeekArc, defStyle, 0);
            progressColor = typedArray.getColor(R.styleable.SeekArc_progressColor, progressColor);

        }


        switch (mPattern)
        {
            case D2_Pattern:
                init2DCircleSeekBar();
                break;
            case D3_Pattern:
                init3DCircleSeekBar();
                break;
            default:
                break;
        }

        setProgressBarColor(progressColor);
    }

    private void init3DCircleSeekBar()
    {
        mCircleSeekNumberBarLeft = new CircleSeekNumberBar(mContext);
        mCircleSeekNumberBarLeft.setLayoutParams(layoutParams);
        addView(mCircleSeekNumberBarLeft);

        mCircleSeekNumberBarRight = new CircleSeekNumberBar(mContext);
        mCircleSeekNumberBarRight.setLayoutParams(layoutParams);
        addView(mCircleSeekNumberBarRight);
    }

    private void init2DCircleSeekBar()
    {
        mCircleSeekNumberBarLeft = new CircleSeekNumberBar(mContext);
        mCircleSeekNumberBarLeft.setLayoutParams(layoutParams);
        addView(mCircleSeekNumberBarLeft);
    }

    /**
     * 设置CircleSeekBar的显示模式，
     *
     * @param pattern
     *            = 2:代表2D模式 pattern = 3 ：代表3D模式（即左右分屏显示字幕）
     * */
    public void setCircleSeekBarPattern(int pattern)
    {
        switch (pattern)
        {
            case D2_Pattern:
                mCircleSeekNumberBarLeft.setVisibility(View.VISIBLE);
                mCircleSeekNumberBarRight.setVisibility(View.GONE);
                break;
            case D3_Pattern:
                mCircleSeekNumberBarLeft.setVisibility(View.VISIBLE);
                mCircleSeekNumberBarRight.setVisibility(View.VISIBLE);
                break;

            default:
                break;
        }
    }

    /**
     * 设置组件显示进度
     * */
    public void setProgress(int progress)
    {
        mCircleSeekNumberBarLeft.setProgress(progress);
        mCircleSeekNumberBarRight.setProgress(progress);
    }

    public void setMax(int max)
    {
        mCircleSeekNumberBarLeft.setMax(max);
        mCircleSeekNumberBarRight.setMax(max);
    }

    public void setProgressBarColor(int color)
    {
        mCircleSeekNumberBarLeft.setProgressBarColor(color);
        mCircleSeekNumberBarRight.setProgressBarColor(color);
    }
}
