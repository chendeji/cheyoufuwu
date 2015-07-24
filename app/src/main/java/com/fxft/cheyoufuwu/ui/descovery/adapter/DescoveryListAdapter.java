package com.fxft.cheyoufuwu.ui.descovery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.interfase.IActivity;
import com.fxft.cheyoufuwu.common.util.BusProvider;
import com.fxft.cheyoufuwu.model.iinterface.IAutoSense;
import com.fxft.cheyoufuwu.ui.descovery.event.OnAutoSenseListDataChangeEvent;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenDJ on 2015/7/23.<br>
 */
public class DescoveryListAdapter extends BaseAdapter implements IActivity{
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<IAutoSense> mAutoSense = new ArrayList<>();

    public DescoveryListAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        BusProvider.getInstance().register(this);
    }

    @Subscribe
    public void onDescoveryDataChangeEvent(OnAutoSenseListDataChangeEvent event) {
        List<IAutoSense> autoSenses = event.getAutoSenses();
        if (autoSenses == null || autoSenses.isEmpty())
            return;
        mAutoSense.clear();
        mAutoSense.addAll(autoSenses);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mAutoSense.size();
    }

    @Override
    public Object getItem(int position) {
        return mAutoSense.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_discor_sort, null, false);
            holder.ivAutoSenseImage = (ImageView) convertView.findViewById(R.id.iv_auto_sense_image);
            holder.tvAutoSenseName = (TextView) convertView.findViewById(R.id.tv_auto_sense_name);
            holder.tvAutoSenseCatalog = (TextView) convertView.findViewById(R.id.tv_auto_sense_catalog);
            holder.tvAutoSenseUpdateTime = (TextView) convertView.findViewById(R.id.tv_auto_sense_update_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        IAutoSense autoSense = (IAutoSense) getItem(position);
        holder.tvAutoSenseName.setText(autoSense.getName());
        holder.tvAutoSenseCatalog.setText(autoSense.getCategory());
        holder.tvAutoSenseUpdateTime.setText(autoSense.getDate());
        Glide.with(mContext).load(autoSense.getPhotoUrl()).into(holder.ivAutoSenseImage);
        return convertView;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestory() {
        BusProvider.getInstance().unregister(this);
    }

    class ViewHolder {
        ImageView ivAutoSenseImage;
        TextView tvAutoSenseName;
        TextView tvAutoSenseCatalog;
        TextView tvAutoSenseUpdateTime;
    }
}
