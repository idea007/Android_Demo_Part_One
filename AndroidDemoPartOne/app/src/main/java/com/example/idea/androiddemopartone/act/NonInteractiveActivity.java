package com.example.idea.androiddemopartone.act;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.idea.androiddemopartone.renderer.OpenGlRenderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by idea on 16/4/5.
 * 本示例演示OpenGL ES开发3D应用
 * 该Activity直接使用了GLSurfaceView
 * 这是因为GLSurfaceView可以直接使用，除非需要接受用户输入，和用户交互，才需要重写一些GLSurfaceView的方法
 * 如果开发一个非交互式的OpenGL应用，可以直接使用GLSurfaceView。参照本示例
 */
public class NonInteractiveActivity extends AppCompatActivity {

    private GLSurfaceView mGLView;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        mGLView = new GLSurfaceView(this);
        //这里需要指定一个自定义的渲染器
        mGLView.setRenderer(new OpenGlRenderer());

        setContentView(mGLView);


    }


    public void onPause() {
        super.onPause();
        mGLView.onPause(); //当Activity暂停时，告诉GLSurfaceView也停止渲染，并释放资源。
    }

    public void onResume() {
        super.onResume();
        mGLView.onResume(); //当Activity恢复时，告诉GLSurfaceView加载资源，继续渲染。
    }


}

class DemoRenderer implements GLSurfaceView.Renderer {

    @Override
    public void onDrawFrame(GL10 gl) {
        //每帧都需要调用该方法进行绘制。绘制时通常先调用glClear来清空framebuffer。
        //然后调用OpenGL ES其他接口进行绘制

        //清除屏幕和深度缓存
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int w, int h) {
        //当surface的尺寸发生改变时，该方法被调用，。往往在这里设置ViewPort。或者Camara等。

        // 设置画面的大小
        gl.glViewport(0, 0, w, h);
        // 设置投影矩阵
        gl.glMatrixMode(GL10.GL_PROJECTION);
        // 重置投影矩阵
        gl.glLoadIdentity();
        // 设置画面比例
        GLU.gluPerspective(gl, 45.0f, (float) w / (float) h, 0.1f,100.0f);
        // 选择模型观察矩阵
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        // 重置模型观察矩阵
        gl.glLoadIdentity();


    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // 该方法在渲染开始前调用，OpenGL ES的绘制上下文被重建时也会调用。
        //当Activity暂停时，绘制上下文会丢失，当Activity恢复时，绘制上下文会重建。
        //do nothing special

        // 黑色背景
        gl.glClearColor(0.0f, 1.0f, 0.0f, 0.5f);
        // 启用阴影平滑（不是必须的）
        gl.glShadeModel(GL10.GL_SMOOTH);

        // 设置深度缓存
        gl.glClearDepthf(1.0f);
        // 启用深度测试
        gl.glEnable(GL10.GL_DEPTH_TEST);
        // 所作深度测试的类型
        gl.glDepthFunc(GL10.GL_LEQUAL);
        // 对透视进行修正
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);


    }

}