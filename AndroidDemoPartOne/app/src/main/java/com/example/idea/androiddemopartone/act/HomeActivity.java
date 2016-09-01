package com.example.idea.androiddemopartone.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.idea.androiddemopartone.R;
import com.example.idea.androiddemopartone.adapter.HomeListAdapter;
import com.example.idea.androiddemopartone.utils.ActUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.lv_list)
    ListView lv_list;

    private HomeListAdapter adapter;

    private String[] titles = {"Android studio 上的一个jniDemo", "GlSurfaceView行为", "GlSurfaceView交互行为", "GlSurfaceView播放本地视频", "自定义progressBar", "SurfaceTexture", "LoopViewPager"
            , "CircleMenu", "CycleScrollView", "获取图片色值", "OpenGl ES2.0 3D开发案例", "Android客户端与PC服务器通过socket进行交互实例"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        adapter = new HomeListAdapter(this, titles);
        lv_list.setAdapter(adapter);

        lv_list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                ActUtils.toJniTestAct(this, false);
                break;
            case 1:
                ActUtils.toNoninteractiveAct(this, false);
                break;
            case 2:
                ActUtils.toInteractiveAct(this, false);
                break;
            case 4:
                ActUtils.toProgressBarAct(this, false);

                break;
            case 5:
                ActUtils.toSurfaceTextureAct(this, false);

                break;
            case 6:
                ActUtils.toLoopViewPagerAct(this, false);

                break;
            case 7:
                ActUtils.toCircleMenuAct(this, false);

                break;
            case 8:
                ActUtils.toCycleScrollViewAct(this, false);

                break;

            case 9:
                ActUtils.toPictureAnalysisAct(this, false);

                break;
            case 10:
                ActUtils.toOpenGlESSampleListAct(this, false);
                break;
            case 11:
                ActUtils.toSocket_AndroidAct(this, false);
                break;
            default:
                break;
        }

    }
}
