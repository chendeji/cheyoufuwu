package com.fxft.cheyoufuwu.ui.mall.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.util.BusProvider;
import com.fxft.cheyoufuwu.common.view.SpanableTextView;
import com.fxft.cheyoufuwu.model.iinterface.IMerchandise;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ChenDJ on 2015/7/22.<br>
 */
public class RecommendMerchandiseItem extends LinearLayout {
    @Bind(R.id.iv_merchandise_image)
    ImageView ivMerchandiseImage;
    @Bind(R.id.tv_merchandise_name)
    TextView tvMerchandiseName;
    @Bind(R.id.tv_merchandise_detail)
    TextView tvMerchandiseDetail;
    @Bind(R.id.tv_merchandise_current_price)
    SpanableTextView tvMerchandiseCurrentPrice;
    @Bind(R.id.tv_merchandise_list_price)
    SpanableTextView tvMerchandiseListPrice;

    public RecommendMerchandiseItem(Context context) {
        this(context, null);
    }

    public RecommendMerchandiseItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onDetachedFromWindow() {
        ButterKnife.unbind(this);
        BusProvider.getInstance().unregister(this);
        super.onDetachedFromWindow();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.mall_recommend_merchandise_item_layout, this, true);
        ButterKnife.bind(this);
        BusProvider.getInstance().register(this);
    }

    public void setComponentValue(IMerchandise iMerchandise) {
        if (iMerchandise == null)
            return;
        tvMerchandiseName.setText(iMerchandise.getName());
        tvMerchandiseDetail.setText(iMerchandise.getDetail());
        tvMerchandiseCurrentPrice.reset();
        tvMerchandiseCurrentPrice.addPiece(new SpanableTextView.Piece.Builder("¥")
                .textColor(getResources().getColor(R.color.light_red))
                .textSizeRelative(1f)
                .build());
        tvMerchandiseCurrentPrice.addPiece(new SpanableTextView.Piece.Builder(String.valueOf(iMerchandise.getCurrentPrice()))
                .textColor(getResources().getColor(R.color.light_red))
                .textSizeRelative(1.2f)
                .build());
        tvMerchandiseCurrentPrice.display();

        tvMerchandiseListPrice.reset();
        tvMerchandiseListPrice.addPiece(new SpanableTextView.Piece.Builder("¥")
                .textColor(getResources().getColor(R.color.light_gray))
                .textSizeRelative(0.7f)
                .build());
        tvMerchandiseListPrice.addPiece(new SpanableTextView.Piece.Builder(String.valueOf(iMerchandise.getListPrice()))
                .textColor(getResources().getColor(R.color.light_gray))
                .strike()
                .textSizeRelative(0.7f)
                .build());
        tvMerchandiseListPrice.display();
        Glide.with(getContext()).load(iMerchandise.getMerchantdisePhoto()).into(ivMerchandiseImage);
    }
}
