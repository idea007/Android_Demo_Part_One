package com.example.idea.androiddemopartone.act;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.idea.androiddemopartone.R;
import com.example.idea.androiddemopartone.utils.DrawableUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PictureAnalysisActivity extends AppCompatActivity {


    @Bind(R.id.tv_show)
    TextView tv_show;

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_picture_analysis);
        ButterKnife.bind(this);

        initView();

        loadData();

    }

    private void loadData() {
        Drawable drawable=getResources().getDrawable(R.mipmap.ic_avatar1);
        bitmap= DrawableUtils.drawableToBitamp_2(drawable);
        histEqualize(bitmap);
    }

    private void initView() {
    }

    /*
     *直方图均衡化
     */
    public Bitmap histEqualize(Bitmap myBitmap){
        // Create new array
        int width = myBitmap.getWidth();
        int height = myBitmap.getHeight();
        int[] pix = new int[width * height];
        myBitmap.getPixels(pix, 0, width, 0, 0, width, height);

        int clr;
        int red,green,blue,tempRed=0,tempBlue=0,tempGreen=0;
        for(int i=0;i<pix.length;i++){
            clr=pix[i];
            red   = (clr & 0x00ff0000) >> 16;  //取高两位
            green = (clr & 0x0000ff00) >> 8; //取中两位
            blue  =  clr & 0x000000ff; //取低两位

            tempRed+=red;
            tempGreen+=green;
            tempBlue+=blue;
        }

        red=tempRed/pix.length;
        green=tempGreen/pix.length;
        blue=tempBlue/pix.length;

        tv_show.setText("红色："+red+" 绿色："+green+" 蓝色："+blue);

//        Matrix dataR=getDataR(pix, width, height);
//        Matrix dataG=getDataG(pix, width, height);
//        Matrix dataB=getDataB(pix, width, height);
        //Matrix dataGray=getDataGray(pix, width, height);
        /////////////////////////////////////////////////////////
//        dataR=eachEqualize(dataR,width,height);
//        dataG=eachEqualize(dataG,width,height);
//        dataB=eachEqualize(dataB,width,height);
//        ///////////////////////////////////////////////////////////////
//        // Change bitmap to use new array
//        Bitmap bitmap=makeToBitmap(dataR, dataG, dataB, width, height);
        myBitmap = null;
        pix = null;
//        return bitmap;
        return null;
    }
//    private Matrix eachEqualize(Matrix temp,int width,int height){
//        // 灰度映射表
//        int bMap[]=new int[256];
//        // 灰度映射表
//        int lCount[]=new int[256];
//        // 重置计数为0
//        int i,j;
//        for (i = 0; i < 256; i ++){
//            // 清零
//            lCount[i] = 0;
//        }
//        // 计算各个灰度值的计数 - 参考灰度直方图的绘制代码 (对话框类中)
//        for (i = 0; i < height; i ++){
//            for (j = 0; j < width; j ++){
//                lCount[(int)temp.get(i, j)]++;  // 计数加1
//            }
//        }
//        // 计算灰度映射表
//        for (i = 0; i < 256; i++){
//            // 初始为0
//            int Temp = 0;
//            for (j = 0; j <= i ; j++){
//                Temp += lCount[j];
//            }
//            // 计算对应的新灰度值
//            bMap[i] = (int) (Temp * 255 / height / width);
//        }
//        // 每行
//        for (i = 0; i < height; i++){
//            // 每列
//            for (j = 0; j < width; j++){
//                temp.set(i, j, bMap[(int)temp.get(i,j)]);
//            }
//        }
//        return temp;
//    }
//
//    private Matrix getDataR(int[] pix,int width,int height){
//        Matrix dataR=new Matrix(width,height,0.0);
//        // Apply pixel-by-pixel change
//        int index = 0;
//        for (int y = 0; y < height; y++)
//        {
//            for (int x = 0; x < width; x++)
//            {
//                int r = ((pix[index] >> 16) & 0xff);
//                dataR.set(x, y, r);
//                index++;
//            } // x
//        } // y
//        return dataR;
//    }
//    private Matrix getDataG(int[] pix,int width,int height){
//        Matrix dataG=new Matrix(width,height,0.0);
//        // Apply pixel-by-pixel change
//        int index = 0;
//        for (int y = 0; y < height; y++)
//        {
//            for (int x = 0; x < width; x++)
//            {
//                int g = ((pix[index] >> 8) & 0xff);
//                dataG.set(x, y, g);
//                index++;
//            } // x
//        } // y
//        return dataG;
//    }
//    private Matrix getDataB(int[] pix,int width,int height){
//        Matrix dataB=new Matrix(width,height,0.0);
//        // Apply pixel-by-pixel change
//        int index = 0;
//        for (int y = 0; y < height; y++)
//        {
//            for (int x = 0; x < width; x++)
//            {
//                int b =(pix[index] & 0xff);
//                dataB.set(x, y, b);
//                index++;
//            } // x
//        } // y
//        return dataB;
//    }
//    private Matrix getDataGray(int[] pix,int width,int height){
//        Matrix dataGray=new Matrix(width,height,0.0);
//        // Apply pixel-by-pixel change
//        int index = 0;
//        for (int y = 0; y < height; y++)
//        {
//            for (int x = 0; x < width; x++)
//            {
//                int r = ((pix[index] >> 16) & 0xff);
//                int g = ((pix[index] >> 8) & 0xff);
//                int b = (pix[index] & 0xff);
//                int gray=(int)(0.3*r+0.59*g+0.11*b);
//                dataGray.set(x, y, gray);
//                index++;
//            } // x
//        } // y
//        return dataGray;
//    }

//    public Bitmap rotate90(Bitmap myBitmap) {
//        // Create new array
//        int width = myBitmap.getWidth();
//        int height = myBitmap.getHeight();
//        int[] pix = new int[width * height];
//        myBitmap.getPixels(pix, 0, width, 0, 0, width, height);
//        Matrix dataR=getDataR(pix, width, height);
//        Matrix dataG=getDataG(pix, width, height);
//        Matrix dataB=getDataB(pix, width, height);
//        //Matrix dataGray=getDataGray(pix, width, height);
//        /////////////////////////////////////////////////////////
//        dataR=dataR.transpose();
//        dataG=dataG.transpose();
//        dataB=dataB.transpose();
//        ///////////////////////////////////////////////////////////////
//        // Change bitmap to use new array
//        Bitmap bitmap=makeToBitmap(dataR, dataG, dataB, width, height);
//        myBitmap = null;
//        pix = null;
//        return bitmap;
//    }
//
//    public Bitmap rotate90(Bitmap myBitmap) {
//        // Create new array
//        int width = myBitmap.getWidth();
//        int height = myBitmap.getHeight();
//        int[] pix = new int[width * height];
//        myBitmap.getPixels(pix, 0, width, 0, 0, width, height);
//        Matrix dataR=getDataR(pix, width, height);
//        Matrix dataG=getDataG(pix, width, height);
//        Matrix dataB=getDataB(pix, width, height);
//        //Matrix dataGray=getDataGray(pix, width, height);
//        /////////////////////////////////////////////////////////
//        dataR=dataR.transpose();
//        dataG=dataG.transpose();
//        dataB=dataB.transpose();
//        ///////////////////////////////////////////////////////////////
//        // Change bitmap to use new array
//        Bitmap bitmap=makeToBitmap(dataR, dataG, dataB, width, height);
//        myBitmap = null;
//        pix = null;
//        return bitmap;
//    }







}
