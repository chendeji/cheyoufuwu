package com.fxft.cheyoufuwu.ui.homePage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.model.iinterface.ICity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenDJ on 2015/7/27.<br>
 */
public class SortAdapter extends BaseAdapter {
    private List<ICity> cities = new ArrayList<>();
    private Context mContext;

    public SortAdapter(Context mContext) {
        this.mContext = mContext;
    }

    /**
     *
     * @param list
     */
    public void updateListView(List<ICity> list){
        this.cities.clear();
        this.cities.addAll(list);
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.cities.size();
    }

    public Object getItem(int position) {
        return cities.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final ICity city = cities.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.choose_city_section_item, null);
            viewHolder.llTitle = (LinearLayout) view.findViewById(R.id.ll_section_holder);
            viewHolder.tvTitle = (TextView) viewHolder.llTitle.findViewById(R.id.tv_section_title);
            viewHolder.tvLetter = (TextView) view.findViewById(R.id.tv_item_title);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        int section = getSectionForPosition(position);

        if(position == getPositionForSection(section)){
            viewHolder.llTitle.setVisibility(View.VISIBLE);
            viewHolder.tvTitle.setText(String.valueOf(getSectionForPosition(position)));
        }else{
            viewHolder.llTitle.setVisibility(View.GONE);
        }
        viewHolder.tvLetter.setText(this.cities.get(position).getCityName());
        return view;
    }

    final static class ViewHolder {
        TextView tvLetter;
        LinearLayout llTitle;
        TextView tvTitle;
    }


    /**
     *
     */
    public char getSectionForPosition(int position) {
        return cities.get(position).getFirstSpell().toUpperCase().charAt(0);
    }

    /**
     *
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = cities.get(i).getFirstSpell();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }
}