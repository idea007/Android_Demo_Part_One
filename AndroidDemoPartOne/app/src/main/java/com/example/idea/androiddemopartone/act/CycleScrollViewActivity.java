package com.example.idea.androiddemopartone.act;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.idea.androiddemopartone.R;
import com.example.idea.androiddemopartone.adapter.AppCycleScrollAdapter;
import com.example.idea.androiddemopartone.view.CycleScrollView.CycleScrollView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CycleScrollViewActivity extends AppCompatActivity{


    @Bind(R.id.cycle_scroll_view)
    CycleScrollView<PackageInfo> mCycleScrollView;

    AppCycleScrollAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cyclescroll);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {

        List<PackageInfo> list = this.getPackageManager()
                .getInstalledPackages(0);

        mAdapter = new AppCycleScrollAdapter(list, mCycleScrollView, this);
    }


}
