package com.fxft.cheyoufuwu.ui.homePage.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.util.BusProvider;
import com.fxft.cheyoufuwu.model.iinterface.IMerchandise;
import com.fxft.cheyoufuwu.ui.homePage.event.OnGuessLikeDataChangeEvent;
import com.squareup.otto.Subscribe;

import java.util.List;

/**
 * 动态添加子项
 * Created by ChenDJ on 2015/7/22.<br>
 */
public class ListLinearLayout extends LinearLayout {

    private LayoutInflater mLayoutInflater;

    public ListLinearLayout(Context context) {
        this(context, null);
    }

    public ListLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        BusProvider.getInstance().register(this);
        init();
    }

    private void init() {
        mLayoutInflater = LayoutInflater.from(getContext());
    }

    @Override
    protected void onDetachedFromWindow() {
        BusProvider.getInstance().unregister(this);
        super.onDetachedFromWindow();
    }

    @Subscribe
    public void onGuessListDataChangeEvent(OnGuessLikeDataChangeEvent event) {
        this.removeAllViews();
        List<IMerchandise> merchandises = event.getMerchandises();
        if (merchandises == null || merchandises.isEmpty())
            return;
        ViewHolder holder;
        IMerchandise merchandise;
        for (int i = 0; i < merchandises.size(); i++) {
            merchandise = merchandises.get(i);
            holder = new ViewHolder();
            holder.tvComment.setText(merchandise.getDetail());
            holder.tvName.setText(merchandise.getName());
            holder.tvPrice.setText("¥"+merchandise.getCurrentPrice());
            holder.tvOldPrice.setText("¥"+merchandise.getListPrice());
            holder.tvDistance.setText(merchandise.getDistance()+"km");
            Glide.with(getContext()).load(merchandise.getMerchantdisePhoto()).into(holder.ivIcon);
            addView(holder.itemView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                    , ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }

    class ViewHolder {
        View itemView;
        ImageView ivIcon;
        TextView tvName;
        TextView tvComment;
        TextView tvPrice;
        TextView tvOldPrice;
        TextView tvDistance;

        public ViewHolder() {
            itemView = mLayoutInflater.inflate(R.layout.home_guess_item, null, false);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvComment = (TextView) itemView.findViewById(R.id.tv_comment);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            tvOldPrice = (TextView) itemView.findViewById(R.id.tv_old_price);
            tvDistance = (TextView) itemView.findViewById(R.id.tv_distance);
        }
    }


}
