package com.example.idea.androiddemopartone.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.example.idea.androiddemopartone.R;
import com.example.idea.androiddemopartone.view.CameraView;

import butterknife.ButterKnife;

/**
 * surfaceTexture和OpenGL ES结合才能发挥出它最大的效果，我这种写法只是我自己的想法，还有很多种解决方法，如果大家学习一下OpenGL ES，会发现更多有意思的东西。

 SurfaceTexture是从Android3.0（API 11）加入的一个新类。这个类跟SurfaceView很像，可以从camera preview或者
 video decode里面获取图像流（image stream）。但是，和SurfaceView不同的是，SurfaceTexture在接收图像流之后，
 不需要显示出来。有做过Android camera开发的人都知道，比较头疼的一个问题就是，从camera读取到的预览（preview）
 图像流一定要输出到一个可见的（Visible）SurfaceView上，然后通过Camera.PreviewCallback的public void onPreviewFrame(byte[] data,
 Camera camera)函数来获得图像帧数据的拷贝。这就存在一个问题，比如我希望隐藏摄像头的预览图像或者对每一帧进行一些处理再显示到手机显示屏上，
 那么在Android3.0之前是没有办法做到的，或者说你需要用一些小技巧，比如用其他控件把SurfaceView给挡住，
 注意这个显示原始camera图像流的SurfaceView其实是依然存在的，也就是说被挡住的SurfaceView依然在接收从camera传过来的图像，
 而且一直按照一定帧率去刷新，这是消耗cpu的，而且如果一些参数设置的不恰当，后面隐藏的SurfaceView有可能会露出来，
 因此这些小技巧并不是好办法。但是，有了SurfaceTexture之后，就好办多了，因为SurfaceTexture不需要显示到屏幕上，
 因此我们可以用SurfaceTexture接收来自camera的图像流，然后从SurfaceTexture中取得图像帧的拷贝进行处理，
 处理完毕后再送给另一个SurfaceView用于显示即可。

 在我的应用场景中，我需要从camera读取图像流，然后对其处理，最后将处理结果显示到手机屏幕上。
 因此我写了一个GameDisplay类用于处理以上所有事务，而在MainActivity中，只需要创建一个GameDisplay类的实例并且初始化即可。

 */
public class SurfaceTextureActivity extends AppCompatActivity{

    public final static String TAG="MainActivity";
    public int screenWidth, screenHeight;
    public int previewWidth, previewHeight;
    private CameraView cameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_surfacetexture);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        cameraView= new CameraView(this,dm.widthPixels,dm.heightPixels);
        FrameLayout root = (FrameLayout) findViewById(R.id.root);
        //'index' indicates the order of the view. 0 means the view will behind all
        //other views. root.getChildCount() means the top
        root.addView(cameraView,0);
        Log.v(TAG, "createUI completed");
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            this.finish();
            System.exit(0);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

}
