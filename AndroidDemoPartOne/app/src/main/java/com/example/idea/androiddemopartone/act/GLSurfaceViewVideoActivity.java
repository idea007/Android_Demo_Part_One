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

public class GLSurfaceViewVideoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @Bind(R.id.lv_list)
    ListView lv_list;

    private HomeListAdapter adapter;

    private String[] titles={"Android studio 上的一个jniDemo","GlSurfaceView播放本地视频"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        adapter=new HomeListAdapter(this,titles);
        lv_list.setAdapter(adapter);

        lv_list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch(position){
            case 0:
                ActUtils.toJniTestAct(this,false);
                break;
            default:
                break;
        }

    }
}
