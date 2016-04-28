package com.example.idea.androiddemopartone.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.idea.androiddemopartone.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class JniTestTwoActivity extends AppCompatActivity {

    //添加静态代码块，作用是运行的时候动态调用名为“MyJni”的动态库
    static{
        System.loadLibrary("MyJni");
    }

    //声明一个native方法，该方法需要后续我们在.c文件中实现
    public native String getStringFromNative();


    @Bind(R.id.tv_show)
    TextView tv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_jni_test_two);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        tv_show.setText(getStringFromNative());
    }


}
