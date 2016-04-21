package com.example.idea.androiddemopartone.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.idea.androiddemopartone.R;

/**
 * Created by idea on 16/4/21.
 */
public class ThreeDProgressbar extends LinearLayout
{
    public Context mContext;
    public int mPattern;
    public static final int D2_Pattern = 2;
    public static final int D3_Pattern = 3;

    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT, 1);

    private NumberProgressBar mProgressbarLeft;
    private NumberProgressBar mProgressbarRight;

    private TypedArray mTypedArray;
    // 进度条选中部分的颜色
    private int reachedColor = Color.rgb(66, 145, 241);
    // 进度条显示字号
    private float textSize;
    // 进度条文字颜色
    private int textColor;

    public ThreeDProgressbar(Context context, int showPattern)
    {
        super(context);
        this.mContext = context;
        this.mPattern = showPattern;
        init();

    }

    public ThreeDProgressbar(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.mContext = context;
        this.mPattern = D3_Pattern;

        mTypedArray = mContext.obtainStyledAttributes(attrs,
                R.styleable.ThreeDProgressbar);

        reachedColor = mTypedArray.getColor(
                R.styleable.ThreeDProgressbar_reached_color,
                Color.rgb(66, 145, 241));
        textSize = mTypedArray.getDimension(
                R.styleable.ThreeDProgressbar_p_text_size, sp2px(10));
        textColor = mTypedArray.getColor(
                R.styleable.ThreeDProgressbar_p_text_color, Color.rgb(66, 145, 241));

        init();

        mTypedArray.recycle();
    }

    private void init()
    {
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_HORIZONTAL);

        switch (mPattern)
        {
            case D2_Pattern:
                init2DProgressbar();
                break;
            case D3_Pattern:
                init3DProgressbar();
                break;
            default:
                break;
        }

    }

    /**
     * 设置2D模式的进度条
     * */
    private void init2DProgressbar()
    {
        mProgressbarLeft = new NumberProgressBar(mContext);

        mProgressbarLeft.setReachedBarColor(reachedColor);
        mProgressbarLeft.setProgressTextColor(textColor);
        mProgressbarLeft.setProgressTextSize(textSize);
        mProgressbarLeft.setPadding(150, 0, 150, 0);

        addView(mProgressbarLeft, layoutParams);

    }

    /**
     * 设置3D模式的进度条
     * */
    private void init3DProgressbar()
    {

        mProgressbarLeft = new NumberProgressBar(mContext);
        mProgressbarLeft.setReachedBarColor(reachedColor);
        mProgressbarLeft.setProgressTextColor(textColor);
        mProgressbarLeft.setProgressTextSize(textSize);
        mProgressbarLeft.setPadding(100, 0, 100, 0);
        addView(mProgressbarLeft, layoutParams);

        mProgressbarRight = new NumberProgressBar(mContext);
        mProgressbarRight.setReachedBarColor(reachedColor);
        mProgressbarRight.setProgressTextColor(textColor);
        mProgressbarRight.setProgressTextSize(textSize);
        mProgressbarRight.setPadding(100, 0, 100, 0);
        addView(mProgressbarRight, layoutParams);

    }

    public void setProgressBarPattern(int pattern)
    {
        switch (pattern)
        {
            case D2_Pattern:
                mProgressbarLeft.setVisibility(View.VISIBLE);
                mProgressbarRight.setVisibility(View.GONE);
                break;
            case D3_Pattern:
                mProgressbarLeft.setVisibility(View.VISIBLE);
                mProgressbarRight.setVisibility(View.VISIBLE);
                break;

            default:
                break;
        }
    }

    /**
     * 设置进度条的最大值
     * */
    public void setMax(int max)
    {

        mProgressbarLeft.setMax(max);
        if (mProgressbarRight != null)
        {
            mProgressbarRight.setMax(max);
        }
    }

    /**
     * 设置进度条的播放进度
     * */
    public void setProgress(int progress)
    {
        mProgressbarLeft.setProgress(progress);
        if (mProgressbarRight != null)
        {
            mProgressbarRight.setProgress(progress);
        }
    }

    public float sp2px(float sp)
    {
        final float scale = getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }
}
