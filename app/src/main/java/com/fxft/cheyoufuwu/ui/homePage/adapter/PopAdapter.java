package com.fxft.cheyoufuwu.ui.homePage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fxft.cheyoufuwu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenDJ on 2015/7/28.<br>
 */
public class PopAdapter extends BaseAdapter {

    private final Context mContext;
    List<String> itemValue = new ArrayList<>();

    public PopAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(String[] arr) {
        itemValue.clear();
        for (String s : arr) {
            itemValue.add(s);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return itemValue.size();
    }

    @Override
    public Object getItem(int position) {
        return itemValue.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.merchant_search_mode_item_layout, null, false);
            holder.tv_text = (TextView) convertView.findViewById(R.id.tv_item_text);
            holder.viewDivider = convertView.findViewById(R.id.view_divider);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position == itemValue.size() - 1) {
            holder.viewDivider.setVisibility(View.GONE);
        } else {
            holder.viewDivider.setVisibility(View.VISIBLE);
        }
        String item = (String) getItem(position);
        holder.tv_text.setText(item);
        return convertView;
    }

    class ViewHolder {
        TextView tv_text;
        View viewDivider;
    }
}
