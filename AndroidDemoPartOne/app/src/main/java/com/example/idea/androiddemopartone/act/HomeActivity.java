package com.example.idea.androiddemopartone.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.idea.androiddemopartone.R;

import butterknife.Bind;

public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.lv_list)
    ListView lv_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);
    }
}
