package com.example.idea.androiddemopartone.act;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
/**
 * 本示例演示OpenGL ES开发3D应用
 * 该Activity使用了自定义的GLSurfaceView的子类
 * 这样，我们可以开发出和用户交互的应用，比如游戏等。
 * 需要注意的是：由于渲染对象是运行在一个独立的渲染线程中，所以
 * 需要采用跨线程的机制来进行事件的处理。但是Android提供了一个简便的方法
 * 我们只需要在事件处理中使用queueEvent(Runnable)就可以了.
 *
 * 对于大多数3D应用，如游戏、模拟等都是持续性渲染，但对于反应式应用来说，只有等用户进行了某个操作后再开始渲染。
 * GLSurfaceView支持这两种模式。通过调用方法setRenderMode()方法设置。
 * 调用requestRender()继续渲染。
 *
 *
 *
 * Created by idea on 16/4/5.
 */
public class InteractiveActivity extends AppCompatActivity {

    private GLSurfaceView mGLView;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mGLView = new DemoGLSurfaceView(this); //这里使用的是自定义的GLSurfaceView的子类
        setContentView(mGLView);
    }


    public void onPause(){
        super.onPause();
        mGLView.onPause();
    }

    public void onResume(){
        super.onResume();
        mGLView.onResume();
    }
}
class DemoGLSurfaceView extends GLSurfaceView{
    DemoRenderer2 mRenderer;

    public DemoGLSurfaceView(Context context) {
        super(context);
        //为了可以激活log和错误检查，帮助调试3D应用，需要调用setDebugFlags()。
        this.setDebugFlags(DEBUG_CHECK_GL_ERROR|DEBUG_LOG_GL_CALLS);
        mRenderer = new DemoRenderer2();
        this.setRenderer(mRenderer);
    }

    public boolean onTouchEvent(final MotionEvent event){
        //由于DemoRenderer2对象运行在另一个线程中，这里采用跨线程的机制进行处理。使用queueEvent方法
        //当然也可以使用其他像Synchronized来进行UI线程和渲染线程进行通信。
        this.queueEvent(new Runnable() {

            @Override
            public void run() {

                //TODO:
                mRenderer.setColor(event.getX()/getWidth(), event.getY()/getHeight(), 1.0f);
            }
        });

        return true;
    }

}
/**
 * 这个应用在每一帧中清空屏幕，当tap屏幕时，改变屏幕的颜色。
 * @author Administrator
 *
 */
class DemoRenderer2 implements GLSurfaceView.Renderer{

    private float mRed;
    private float mGreen;
    private float mBlue;
    @Override
    public void onDrawFrame(GL10 gl) {
        // TODO Auto-generated method stub
        gl.glClearColor(mRed, mGreen, mBlue, 1.0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
    }
    @Override
    public void onSurfaceChanged(GL10 gl, int w, int h) {
        // TODO Auto-generated method stub
        gl.glViewport(0, 0, w, h);
    }
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // TODO Auto-generated method stub

    }

    public void setColor(float r, float g, float b){
        this.mRed = r;
        this.mGreen = g;
        this.mBlue = b;
    }

}