package com.example.idea.androiddemopartone.adapter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.idea.androiddemopartone.R;
import com.example.idea.androiddemopartone.view.CycleScrollView.CycleScrollAdapter;
import com.example.idea.androiddemopartone.view.CycleScrollView.CycleScrollView;

import java.util.List;

public class AppCycleScrollAdapter extends CycleScrollAdapter<PackageInfo> {

    public AppCycleScrollAdapter(List<PackageInfo> list,
                                 CycleScrollView<PackageInfo> cycleScrollView, Context context) {
        super(list, cycleScrollView, context);
    }

    @Override
    protected void initView(List<PackageInfo> list) {
        super.initView(list);
    }

    @Override
    public void bindView(View child, PackageInfo pi) {
        ImageView image = (ImageView) child.findViewById(R.id.item_image);
        TextView text = (TextView) child.findViewById(R.id.item_text);
        image.setImageDrawable(pi.applicationInfo.loadIcon(mContext
                .getPackageManager()));
        text.setText(pi.applicationInfo.loadLabel(mContext.getPackageManager()));
    }

    @Override
    public View getView(PackageInfo pi) {
        View view = View.inflate(mContext, R.layout.view_item, null);
        // inflate APP icon view
        ImageView image = (ImageView) view.findViewById(R.id.item_image);
        // inflate APP name view
        TextView text = (TextView) view.findViewById(R.id.item_text);
        image.setImageDrawable(pi.applicationInfo.loadIcon(mContext
                .getPackageManager()));
        text.setText(pi.applicationInfo.loadLabel(mContext.getPackageManager()));
        return view;
    }
}


