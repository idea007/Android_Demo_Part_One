package com.example.idea.androiddemopartone.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by idea on 16/4/21.
 */
public class CircleSeekNumberBar extends FrameLayout
{
    private SeekArc mSeekArc;
    private TextView mTextView;

    private Context mContext;

    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.MATCH_PARENT, 1);

    public CircleSeekNumberBar(Context context)
    {
        super(context);

        init(context);
    }

    public CircleSeekNumberBar(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    private void init(Context context)
    {
        this.mContext = context;

        mSeekArc = new SeekArc(mContext);
        mSeekArc.setLayoutParams(layoutParams);
        mSeekArc.setPadding(300, 300, 300, 300);
        addView(mSeekArc);

        mTextView = new TextView(mContext);
        mTextView.setLayoutParams(layoutParams);
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setText("0");
        addView(mTextView);
    }

    /**
     * 设置组件显示进度
     * */
    public void setProgress(int progress)
    {
        mSeekArc.setProgress(progress);
        mTextView.setText("");
        mTextView.setText("" + progress);
    }

    /**
     * 设置组件的最大值
     * */
    public void setMax(int max)
    {
        mSeekArc.setMax(max);
    }

    /**
     * 设置进度条和中间字体的颜色
     * */
    public void setProgressBarColor(int color)
    {
        mSeekArc.setProgressBarColor(color);
        mTextView.setTextColor(color);
    }

}

