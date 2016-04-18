package com.example.idea.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.idea.myapplication.utils.ActUtils;

public class MainActivity extends AppCompatActivity {

   private Button btn_play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_play= (Button) findViewById(R.id.btn_play);

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActUtils.toVideoPlayerAct(MainActivity.this,false);
            }
        });

    }
}
