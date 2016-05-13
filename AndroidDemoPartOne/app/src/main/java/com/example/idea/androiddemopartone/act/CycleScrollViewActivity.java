package com.example.idea.androiddemopartone.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.idea.androiddemopartone.R;
import com.example.idea.androiddemopartone.adapter.AppCycleScrollAdapter;
import com.example.idea.androiddemopartone.model.ItemInfo;
import com.example.idea.androiddemopartone.view.CycleScrollView.CycleScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CycleScrollViewActivity extends AppCompatActivity{

    private List<ItemInfo> mUserList = new ArrayList<>();
    @Bind(R.id.cycle_scroll_view)
    CycleScrollView<ItemInfo> mCycleScrollView;

    AppCycleScrollAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cyclescroll);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {


        ItemInfo users1 = new ItemInfo("Jimmy", R.mipmap.ic_avatar1);
        ItemInfo users2 = new ItemInfo("Tomson", R.mipmap.ic_avatar2);
        ItemInfo users3 = new ItemInfo("Lucy", R.mipmap.ic_avatar3);

        mUserList.add(users1);
        mUserList.add(users2);
        mUserList.add(users3);
//        mUserList.add(users1);
//        mUserList.add(users2);
//        mUserList.add(users3);
//
//        mUserList.add(users1);
//        mUserList.add(users2);
//        mUserList.add(users3);


//        List<PackageInfo> list = this.getPackageManager()
//                .getInstalledPackages(0);

        mAdapter = new AppCycleScrollAdapter(mUserList, mCycleScrollView, this);
    }


}
