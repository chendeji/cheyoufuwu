package com.fxft.cheyoufuwu.ui.homePage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.interfase.IActivity;
import com.fxft.cheyoufuwu.common.util.BusProvider;
import com.fxft.cheyoufuwu.model.iinterface.IMerchant;
import com.fxft.cheyoufuwu.ui.homePage.event.OnCarMaintainListDataChangeEvent;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenDJ on 2015/7/29.<br>
 */
public class CarMaintainMerchantListAdapter extends BaseAdapter implements IActivity{

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<IMerchant> merchants = new ArrayList<>();

    public CarMaintainMerchantListAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        BusProvider.getInstance().register(this);
    }

    @Subscribe
    public void onMerchantDataChangeEvent(OnCarMaintainListDataChangeEvent event){
        List<IMerchant> merchants = event.getMerchants();
        if (merchants == null || merchants.isEmpty())
            return;
        setData(merchants, event.isRefresh());
    }

    private void setData(List<IMerchant> merchants, boolean isRefresh){
        if (isRefresh){
            this.merchants.clear();
            this.merchants.addAll(merchants);
        } else {
            this.merchants.addAll(merchants);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return merchants.size();
    }

    @Override
    public Object getItem(int position) {
        return merchants.get(position);
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
            convertView = mLayoutInflater.inflate(R.layout.car_maintain_merchant_item_layout, null, false);
            holder.tvMerchantName = (TextView) convertView.findViewById(R.id.tv_merchant_name);
            holder.ivMerchantQuan = (ImageView) convertView.findViewById(R.id.iv_merchant_quan);
            holder.tvSellCount = (TextView) convertView.findViewById(R.id.tv_sell_count);
            holder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.rbMerchantRating = (RatingBar) convertView.findViewById(R.id.rb_merchant_rating);
            holder.tvArea = (TextView) convertView.findViewById(R.id.tv_area);
            holder.tvServiceType = (TextView) convertView.findViewById(R.id.tv_service_type);
            holder.ivFoodService = (ImageView) convertView.findViewById(R.id.iv_food_service);
            holder.ivLightService = (ImageView) convertView.findViewById(R.id.iv_light_service);
            holder.ivVideoService = (ImageView) convertView.findViewById(R.id.iv_video_service);
            holder.ivWifiService = (ImageView) convertView.findViewById(R.id.iv_wifi_service);
            holder.ivTeeService = (ImageView) convertView.findViewById(R.id.iv_tee_service);
            holder.tvDistance = (TextView) convertView.findViewById(R.id.tv_distance);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        IMerchant merchant = (IMerchant) getItem(position);
        holder.tvMerchantName.setText(merchant.getName());
        holder.tvSellCount.setText("成交"+merchant.getSellCount()+"笔");
        holder.rbMerchantRating.setRating(0.0f);
        holder.rbMerchantRating.setRating(merchant.getRating());
        holder.tvArea.setText(merchant.getArea());
        holder.tvServiceType.setText(merchant.getServiceType() == 0 ? "综合服务" : "");
        holder.tvDistance.setText(merchant.getDistance()+"km");

        holder.ivFoodService.setVisibility(merchant.hasSnacksService() ? View.VISIBLE : View.GONE);
        holder.ivLightService.setVisibility(merchant.hasLightService() ? View.VISIBLE : View.GONE);
        holder.ivVideoService.setVisibility(merchant.hasVideoService() ? View.VISIBLE : View.GONE);
        holder.ivWifiService.setVisibility(merchant.hasWifiService() ? View.VISIBLE : View.GONE);
        holder.ivTeeService.setVisibility(merchant.hasTeeService() ? View.VISIBLE : View.GONE);

        Glide.with(mContext).load(merchant.getImageUrl()).into(holder.ivIcon);
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
        ImageView ivMerchantQuan;
        TextView tvSellCount;
        ImageView ivIcon;
        RatingBar rbMerchantRating;
        TextView tvArea;
        TextView tvServiceType;
        ImageView ivFoodService;
        ImageView ivLightService;
        ImageView ivVideoService;
        ImageView ivWifiService;
        ImageView ivTeeService;
        TextView tvDistance;
    }
}
