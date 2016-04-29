package com.example.idea.androiddemopartone.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * 左右两边对称的 系统progressBar
 * @author idea
 *
 */
public class SymmetrySeekBar extends LinearLayout{
	
	public Context mContext;
    public int mPattern;
    public static final int D2_Pattern = 2;
    public static final int D3_Pattern = 3;
	
    LayoutParams layoutParams = new LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT, 1);

    private SeekBar mSeekBarLeft;
    private SeekBar mSeekBarRight;


    public SymmetrySeekBar(Context context) {
        super(context);
        this.mContext=context;
        this.mPattern=D3_Pattern;
        init();
    }

    public SymmetrySeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        this.mPattern=D3_Pattern;
        init();
    }

    public SymmetrySeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        this.mPattern=D3_Pattern;
        init();
    }

    //	public SymmetrySeekBar(Context context,int showPattern) {
//		super(context);
//		// TODO Auto-generated constructor stub
//		this.mContext=context;
//		this.mPattern=showPattern;
//
//		init();
//
//	}
//
//
//	public SymmetrySeekBar(Context context,AttributeSet attrs) {
//		super(context);
//		// TODO Auto-generated constructor stub
//		this.mContext=context;
//		this.mPattern=D3_Pattern;
//		init();
//
//	}
//
	private void init() {
		// TODO Auto-generated method stub
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
    	mSeekBarLeft = new SeekBar(mContext);

    	mSeekBarLeft.setPadding(150, 0, 150, 0);

        addView(mSeekBarLeft, layoutParams);

    }

    /**
     * 设置3D模式的进度条
     * */
    private void init3DProgressbar()
    {
    	mSeekBarLeft = new SeekBar(mContext);
    	mSeekBarLeft.setPadding(100, 0, 100, 0);
        addView(mSeekBarLeft, layoutParams);
        mSeekBarRight = new SeekBar(mContext);
        mSeekBarRight.setPadding(100, 0, 100, 0);
        addView(mSeekBarRight, layoutParams);
    }

    public void setSeekBarPattern(int pattern)
    {
        switch (pattern)
        {
            case D2_Pattern:
            	mSeekBarLeft.setVisibility(View.VISIBLE);
            	mSeekBarRight.setVisibility(View.GONE);
                break;
            case D3_Pattern:
            	mSeekBarLeft.setVisibility(View.VISIBLE);
            	mSeekBarRight.setVisibility(View.VISIBLE);
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

    	mSeekBarLeft.setMax(max);
        if (mSeekBarRight != null)
        {
        	mSeekBarRight.setMax(max);
        }
    }

    /**
     * 设置进度条的播放进度
     * */
    public void setProgress(int progress)
    {
    	mSeekBarLeft.setProgress(progress);
        if (mSeekBarRight != null)
        {
        	mSeekBarRight.setProgress(progress);
        }
    }

    public float sp2px(float sp)
    {
        final float scale = getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }


	public int getMax() {
		return mSeekBarLeft.getMax();
	}


	public int getProgress() {
		// TODO Auto-generated method stub
		return mSeekBarLeft.getProgress();
	}


	public void setOnSeekBarChangeListener(
			OnSeekBarChangeListener onSeekBarChangeListener) {
		mSeekBarLeft.setOnSeekBarChangeListener(onSeekBarChangeListener);
		if(mSeekBarRight!=null){
			mSeekBarRight.setOnSeekBarChangeListener(onSeekBarChangeListener);
		}
		
	}


	public void setSecondaryProgress(int secondaryProgress) {
		// TODO Auto-generated method stub
		mSeekBarLeft.setSecondaryProgress(secondaryProgress);
		if(mSeekBarRight!=null){
			mSeekBarRight.setSecondaryProgress(secondaryProgress);
		}
	}	

}
