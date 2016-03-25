package com.example.idea.androiddemopartone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.idea.androiddemopartone.R;

/**
 * Created by idea on 16/3/25.
 */
public class HomeListAdapter extends BaseAdapter {


    private Context context;
    private String[] datas;

    public HomeListAdapter(Context context, String[] datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.length;
    }

    @Override
    public String getItem(int position) {
        return datas[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null||convertView.getTag()==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home,null);
            holder=new ViewHolder();
            holder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.tv_name.setText(datas[position]);

        return convertView;
    }

    class ViewHolder {
        TextView tv_name;
    }
}
