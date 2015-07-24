package com.fxft.cheyoufuwu.ui.homePage.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fxft.cheyoufuwu.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by taosj on 2015/7/20.
 */
public class MenuItemView extends LinearLayout {

    @Bind(R.id.iv_item_top_iamge)
    ImageView ivItemTopIamge;
    @Bind(R.id.tv_business_name)
    TextView tvBusinessName;
    @Bind(R.id.tv_business_detail)
    TextView tvBusinessDetail;
    private Drawable mTopImage;
    private String mMenuTitle;
    private String mMenuDetail;
    private int mTitleTextColor;
    private int mDetailTextColor;
    private float mTitileTextSize;
    private float mDetailTextSize;
    private boolean mDetailTextEnable;

    public MenuItemView(Context context) {
        this(context, null);
    }

    public MenuItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        ButterKnife.bind(this);
        setComponentValue();
        super.onAttachedToWindow();
    }

    private void setComponentValue() {
        if (mTopImage != null){
            ivItemTopIamge.setBackgroundDrawable(mTopImage);
        }
        tvBusinessName.setText(mMenuTitle);
        tvBusinessName.setTextColor(mTitleTextColor);
        tvBusinessName.setTextSize(mTitileTextSize);
        if (mDetailTextEnable) {
            tvBusinessDetail.setText(mMenuDetail);
            tvBusinessDetail.setTextSize(mDetailTextSize);
            tvBusinessDetail.setTextColor(mDetailTextColor);
        } else {
            tvBusinessDetail.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        ButterKnife.unbind(this);
        super.onDetachedFromWindow();
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.home_menu_item_layout, this, true);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MenuItemView);
        mTopImage = a.getDrawable(R.styleable.MenuItemView_menu_top_drawable);
        mMenuTitle = a.getString(R.styleable.MenuItemView_menu_item_title);
        mMenuDetail = a.getString(R.styleable.MenuItemView_menu_item_detail);
        mTitleTextColor = a.getColor(R.styleable.MenuItemView_menu_title_text_color, Color.BLACK);
        mDetailTextColor = a.getColor(R.styleable.MenuItemView_menu_detail_text_color, Color.BLACK);
        mTitileTextSize = a.getDimension(R.styleable.MenuItemView_menu_title_text_size, getContext().getResources().getDimension(R.dimen.default_text_size));
        mDetailTextSize = a.getDimension(R.styleable.MenuItemView_menu_detail_text_size, getContext().getResources().getDimension(R.dimen.default_text_size));
        mDetailTextEnable = a.getBoolean(R.styleable.MenuItemView_menu_detail_text_enable, true);
        a.recycle();
    }

}
