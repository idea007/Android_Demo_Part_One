package com.example.idea.androiddemopartone.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.example.idea.androiddemopartone.R;

/**
 * Created by idea on 16/4/29.
 */
public class ClockProgressViewcopy extends View {

    private Paint paint;
    private float mBarSize;
    private float mBarThickness;  //厚度
    private int mSize;

    private int progress=0;


    public ClockProgressViewcopy(Context context) {
        super(context);
        init(context);
    }


    public ClockProgressViewcopy(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ClockProgressViewcopy(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBg(canvas, paint); // 绘制表盘
        drawBar(canvas, paint); // 绘制时针、分针、秒针

    }

    private void drawBar(Canvas canvas, Paint paint) {

        paint.setColor(Color.BLACK);
        canvas.save();
        canvas.rotate(progress,mSize/2,mSize/2);
//        Path path=new Path();
//        path.moveTo(mSize/2,mSize/2);
//        path.lineTo(mSize/2,mSize*3/8);
        canvas.drawLine(mSize/2,mSize/2,mSize/8,mSize/2,paint);
//
//
//        canvas.drawPath(path,paint);


        canvas.restore();

        // 时针
        paint.setColor(Color.BLUE);
        canvas.save(); // 线锁定画布
        canvas.rotate(30, mSize / 2, mSize / 2); // 旋转画布
        Path path1 = new Path();
        path1.moveTo(mSize / 2, mSize / 2); // 开始的基点
        path1.lineTo(mSize / 2, mSize / 4); // 最后的基点
        canvas.drawPath(path1, paint);
        canvas.restore();


    }

    private void drawBg(Canvas canvas, Paint paint) {

        paint.setColor(Color.WHITE);
        canvas.drawCircle(mSize/2,mSize/2,mSize*7/16,paint);

    }

    public void setProgress(int progress){
        if(progress<0){
            progress=0;
        }else if(progress>180){
            progress=180;
        }
        this.progress=progress;


        invalidate();

    }

    private void init(Context context) {
        paint=new Paint();


        this.mSize = context.getResources().getDimensionPixelSize(R.dimen.ldrawer_drawableSize);
        this.mBarSize = context.getResources().getDimensionPixelSize(R.dimen.ldrawer_barSize);
        this.mBarThickness = context.getResources().getDimensionPixelSize(R.dimen.ldrawer_thickness);



        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL); // 空心的画笔
        paint.setStrokeWidth(this.mBarThickness); // 设置paint的外框宽度


    }

}
