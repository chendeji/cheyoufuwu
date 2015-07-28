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
import com.fxft.cheyoufuwu.ui.homePage.event.OnWashCarMerchandiseDataChangeEvent;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by ChenDJ on 2015/7/28.<br>
 */
public class CarWashMerchantListAdapter extends BaseAdapter implements IActivity{

    private int currentPageNum = 0;

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;

    private List<IMerchandise> merchandises = new ArrayList<>();

    public CarWashMerchantListAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        BusProvider.getInstance().register(this);
    }

    public int getCurrentPageNum(){
        return this.currentPageNum;
    }

    @Subscribe
    public void onWashCarDataChange(OnWashCarMerchandiseDataChangeEvent event){
        List<IMerchandise> merchandises = event.getMerchandises();
        if (merchandises == null || merchandises.isEmpty())
            return;
        if (event.isRefreshData()){
            setData(merchandises,false);
            return;
        }
        setData(merchandises, true);
    }

    public void setData(List<IMerchandise> merchandises, boolean isAdd){
        if (isAdd){
            this.merchandises.addAll(merchandises);
        } else {
            this.merchandises.clear();
            this.merchandises.addAll(merchandises);
        }
    }

    @Override
    public int getCount() {
        return merchandises.size();
    }

    @Override
    public Object getItem(int position) {
        return merchandises.get(position);
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
            convertView = mLayoutInflater.inflate(R.layout.car_wash_merchant_item, null, false);
            holder.tvMerchantName = (TextView) convertView.findViewById(R.id.tv_merchant_name);
            holder.tvDistance = (TextView) convertView.findViewById(R.id.tv_distance);
            holder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.tvQuanName = (TextView) convertView.findViewById(R.id.tv_quan_name);
            holder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            holder.tvMerchantComment = (TextView) convertView.findViewById(R.id.tv_merchant_comment);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        IMerchandise merchandise = (IMerchandise) getItem(position);
        holder.tvMerchantName.setText(merchandise.getName());
        holder.tvDistance.setText(merchandise.getDistance()+"km");
        holder.tvQuanName.setText(merchandise.getDetail());
        holder.tvPrice.setText("¥"+merchandise.getCurrentPrice());
        holder.tvMerchantComment.setText(String.valueOf(merchandise.getCommentValue())+"分");
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
        BusProvider.getInstance().unregister(this);
    }

    class ViewHolder {
        TextView tvMerchantName;
        TextView tvDistance;
        ImageView ivIcon;
        TextView tvQuanName;
        TextView tvComment;
        TextView tvPrice;
        TextView tvOldPrice;
        TextView tvMerchantComment;
    }
}
