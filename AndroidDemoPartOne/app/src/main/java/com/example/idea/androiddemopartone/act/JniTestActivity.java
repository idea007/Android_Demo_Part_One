package com.example.idea.androiddemopartone.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.idea.androiddemopartone.R;
import com.example.idea.androiddemopartone.adapter.HomeListAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class JniTestActivity extends AppCompatActivity{

    @Bind(R.id.lv_list)
    ListView lv_list;

    private HomeListAdapter adapter;

    private String[] titles={"Android studio 上的一个jniDemo"};
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

    }


}
