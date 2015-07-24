package com.fxft.cheyoufuwu.ui.mall.adapter;

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
import com.fxft.cheyoufuwu.common.view.SpanableTextView;
import com.fxft.cheyoufuwu.model.iinterface.IMerchandise;
import com.fxft.cheyoufuwu.ui.mall.event.OnMerchandiseDataChangeEvent;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenDJ on 2015/7/22.<br>
 */
public class RecommendMerchandiseAdapter extends BaseAdapter implements IActivity {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;

    List<IMerchandise> mMerchandises = new ArrayList<>();

    public RecommendMerchandiseAdapter(Context context) {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        BusProvider.getInstance().register(this);
    }

    @Subscribe
    public void onMerchandiseDataChangeEvent(OnMerchandiseDataChangeEvent event){
        List<IMerchandise> merchandises = event.getMerchandises();
        if (merchandises == null || merchandises.isEmpty())
            return;
        mMerchandises.clear();
        mMerchandises.addAll(merchandises);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mMerchandises.size();
    }

    @Override
    public Object getItem(int position) {
        return mMerchandises.get(position);
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
            convertView = mLayoutInflater.inflate(R.layout.mall_merchandise_list_item_layout, null, false);
            holder.ivMerchandiseImage = (ImageView) convertView.findViewById(R.id.iv_merchantdies_icon);
            holder.tvMerchandiseName = (TextView) convertView.findViewById(R.id.tv_merchantdies_name);
            holder.tvMerchandiseDetail = (TextView) convertView.findViewById(R.id.tv_merchantdies_comment);
            holder.tvMerchandiseCurrentPrice = (SpanableTextView) convertView.findViewById(R.id.tv_merchantdies_price);
            holder.tvMerchandiseListPrice = (SpanableTextView) convertView.findViewById(R.id.tv_merchantdies_old_price);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        IMerchandise merchandise = (IMerchandise) getItem(position);
        Glide.with(mContext).load(merchandise.getMerchantdisePhoto()).into(holder.ivMerchandiseImage);
        holder.tvMerchandiseName.setText(merchandise.getName());
        holder.tvMerchandiseDetail.setText(merchandise.getDetail());
        holder.tvMerchandiseCurrentPrice.reset();
        holder.tvMerchandiseCurrentPrice.addPiece(new SpanableTextView.Piece.Builder("¥")
                .textSizeRelative(1.0f)
                .textColor(mContext.getResources().getColor(R.color.light_red))
                .build());
        holder.tvMerchandiseCurrentPrice.addPiece(new SpanableTextView.Piece.Builder(String.valueOf(merchandise.getCurrentPrice()))
                .textColor(mContext.getResources().getColor(R.color.light_red))
                .textSizeRelative(1.2f)
                .build());
        holder.tvMerchandiseCurrentPrice.display();

        holder.tvMerchandiseListPrice.reset();
        holder.tvMerchandiseListPrice.addPiece(new SpanableTextView.Piece.Builder("¥")
                .textSizeRelative(0.7f)
                .textColor(mContext.getResources().getColor(R.color.light_gray))
                .build());
        holder.tvMerchandiseListPrice.addPiece(new SpanableTextView.Piece.Builder(String.valueOf(merchandise.getListPrice()))
                .textSizeRelative(0.7f)
                .strike()
                .textColor(mContext.getResources().getColor(R.color.light_gray))
                .build());
        holder.tvMerchandiseListPrice.display();

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
        mMerchandises.clear();
        mMerchandises = null;
        BusProvider.getInstance().unregister(this);
    }

    class ViewHolder {
        ImageView ivMerchandiseImage;
        TextView tvMerchandiseName;
        TextView tvMerchandiseDetail;
        SpanableTextView tvMerchandiseCurrentPrice;
        SpanableTextView tvMerchandiseListPrice;
    }
}
