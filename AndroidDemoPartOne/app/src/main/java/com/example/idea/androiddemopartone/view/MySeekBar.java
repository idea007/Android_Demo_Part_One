package com.example.idea.androiddemopartone.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.idea.androiddemopartone.R;

/**
 * Created by idea on 16/4/29.
 */
public class MySeekBar extends View {

    private Paint paint;
    private float mBarThickness;  //厚度
    private int mSize;      //view 中间白色

    private int progress = 0;

    private int height;  //view的高度
    private int width;   //view的宽度


    public MySeekBar(Context context) {
        super(context);
        init(context);
    }


    public MySeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MySeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBg(canvas, paint); // 绘制表盘
        drawBar(canvas, paint); // 绘制时针、分针、秒针
        drawPlusAndMinus(canvas, paint);

    }

    private void drawPlusAndMinus(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.drawLine(width / 24, mSize / 2, width / 8, mSize / 2, paint);
        canvas.drawLine(width * 23 / 24, mSize / 2, width * 7 / 8, mSize / 2, paint);
        canvas.drawLine(width * 22 / 24, mSize / 2 - width / 24, width * 22 / 24, mSize / 2 + width / 24, paint);
        canvas.restore();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.height = getMeasuredHeight();
        this.width = getMeasuredWidth();
        mSize = width * 2 / 3;


    }

    private void drawBar(Canvas canvas, Paint paint) {

        paint.setColor(Color.BLACK);
        canvas.save();
        canvas.rotate(progress, width / 2, mSize / 2);
        canvas.drawLine(width / 2, mSize / 2, width / 4, mSize / 2, paint);

        canvas.restore();

    }

    private void drawBg(Canvas canvas, Paint paint) {

        paint.setColor(Color.WHITE);
        canvas.drawCircle(width / 2, mSize / 2, mSize * 7 / 16, paint);

    }

    public void setProgress(int progress) {
        if (progress < 0) {
            progress = 0;
        } else if (progress > 180) {
            progress = 180;
        }
        this.progress = progress;


        invalidate();

    }

    private void init(Context context) {
        paint = new Paint();


        this.mSize = context.getResources().getDimensionPixelSize(R.dimen.ldrawer_drawableSize);

        this.mBarThickness = context.getResources().getDimensionPixelSize(R.dimen.ldrawer_thickness);


        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL); // 空心的画笔
        paint.setStrokeWidth(this.mBarThickness); // 设置paint的外框宽度


    }

}
