package com.example.idea.androiddemopartone.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.idea.androiddemopartone.R;
import com.example.idea.androiddemopartone.model.ItemInfo;
import com.example.idea.androiddemopartone.view.CycleScrollView.CycleScrollAdapter;
import com.example.idea.androiddemopartone.view.CycleScrollView.CycleScrollView;

import java.util.List;

public class AppCycleScrollAdapter extends CycleScrollAdapter<ItemInfo> {

    public AppCycleScrollAdapter(List<ItemInfo> list,
                                 CycleScrollView<ItemInfo> cycleScrollView, Context context) {
        super(list, cycleScrollView, context);
    }

    @Override
    protected void initView(List<ItemInfo> list) {
        super.initView(list);
    }

    @Override
    public void bindView(View child, ItemInfo pi) {
        ImageView image = (ImageView) child.findViewById(R.id.item_image);
        TextView text = (TextView) child.findViewById(R.id.item_text);
        image.setImageResource(pi.getAvatar());
        text.setText(pi.getName());
    }

    @Override
    public View getView(ItemInfo pi) {
        View view = View.inflate(mContext, R.layout.view_item, null);
        ImageView image = (ImageView) view.findViewById(R.id.item_image);
        TextView text = (TextView) view.findViewById(R.id.item_text);
        image.setImageResource(pi.getAvatar());
        text.setText(pi.getName());
        return view;
    }
}


