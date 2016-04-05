package com.example.idea.androiddemopartone.act;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by idea on 16/4/5.
 * 本示例演示OpenGL ES开发3D应用
 * 该Activity直接使用了GLSurfaceView
 * 这是因为GLSurfaceView可以直接使用，除非需要接受用户输入，和用户交互，才需要重写一些GLSurfaceView的方法
 * 如果开发一个非交互式的OpenGL应用，可以直接使用GLSurfaceView。参照本示例
 *
 *
 */
public class NonInteractiveActivity extends AppCompatActivity {

    private GLSurfaceView mGLView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGLView = new GLSurfaceView(this);
        //这里需要指定一个自定义的渲染器
        mGLView.setRenderer(new DemoRenderer());
        setContentView(mGLView);

    }


    public void onPause(){
        super.onPause();
        mGLView.onPause(); //当Activity暂停时，告诉GLSurfaceView也停止渲染，并释放资源。
    }

    public void onResume(){
        super.onResume();
        mGLView.onResume(); //当Activity恢复时，告诉GLSurfaceView加载资源，继续渲染。
    }




}
class DemoRenderer implements GLSurfaceView.Renderer {
    @Override
    public void onDrawFrame(GL10 gl) {
        //每帧都需要调用该方法进行绘制。绘制时通常先调用glClear来清空framebuffer。
        //然后调用OpenGL ES其他接口进行绘制
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);

    }
    @Override
    public void onSurfaceChanged(GL10 gl, int w, int h) {
        //当surface的尺寸发生改变时，该方法被调用，。往往在这里设置ViewPort。或者Camara等。
        gl.glViewport(0, 0, w, h);
    }
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // 该方法在渲染开始前调用，OpenGL ES的绘制上下文被重建时也会调用。
        //当Activity暂停时，绘制上下文会丢失，当Activity恢复时，绘制上下文会重建。

        //do nothing special
    }

}