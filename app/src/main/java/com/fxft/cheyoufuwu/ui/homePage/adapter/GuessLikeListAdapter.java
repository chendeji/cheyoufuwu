package com.fxft.cheyoufuwu.ui.homePage.adapter;

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
import com.fxft.cheyoufuwu.model.iinterface.IMerchandise;
import com.fxft.cheyoufuwu.ui.homePage.event.OnGuessLikeDataChangeEvent;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenDJ on 2015/7/22.<br>
 */
public class GuessLikeListAdapter extends BaseAdapter implements IActivity {

    private final Context mContext;
    private final LayoutInflater mLayoutinflater;
    private List<IMerchandise> mMerchandise = new ArrayList<>();

    public GuessLikeListAdapter(Context context) {
        this.mContext = context;
        this.mLayoutinflater = LayoutInflater.from(context);
        BusProvider.getInstance().register(this);
    }

    @Subscribe
    public void onGuessLikeDataReceiveEvent(OnGuessLikeDataChangeEvent event) {
        List<IMerchandise> merchandises = event.getMerchandises();
        if (merchandises == null || merchandises.isEmpty())
            return;
        setGuessLikeList(merchandises);
    }

    private void setGuessLikeList(List<IMerchandise> merchandises) {
        merchandises.clear();
        this.mMerchandise.addAll(merchandises);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mMerchandise.size();
    }

    @Override
    public Object getItem(int position) {
        return mMerchandise.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = mLayoutinflater.inflate(R.layout.home_guess_item, null, false);;
            holder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tvComment = (TextView) convertView.findViewById(R.id.tv_comment);
            holder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            holder.tvOldPrice = (TextView) convertView.findViewById(R.id.tv_old_price);
            holder.tvDistance = (TextView) convertView.findViewById(R.id.tv_distance);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        IMerchandise merchandise = (IMerchandise) getItem(position);
        holder.tvName.setText(merchandise.getName());
        holder.tvComment.setText(merchandise.getDetail());
        holder.tvPrice.setText(merchandise.getCurrentPrice());
        holder.tvOldPrice.setText(merchandise.getListPrice());
        holder.tvDistance.setText(merchandise.getDistance());
        Glide.with(mContext).load(merchandise.getMerchantdisePhoto()).into(holder.ivIcon);
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
        mMerchandise.clear();
        mMerchandise = null;
        BusProvider.getInstance().unregister(this);
    }

    class ViewHolder {
        ImageView ivIcon;
        TextView tvName;
        TextView tvComment;
        TextView tvPrice;
        TextView tvOldPrice;
        TextView tvDistance;
    }
}
