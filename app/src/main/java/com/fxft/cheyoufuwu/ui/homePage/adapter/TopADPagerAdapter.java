package com.fxft.cheyoufuwu.ui.homePage.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.fxft.cheyoufuwu.common.interfase.IActivity;
import com.fxft.cheyoufuwu.common.util.BusProvider;
import com.fxft.cheyoufuwu.ui.common.BasePagerAdapter;
import com.fxft.cheyoufuwu.ui.homePage.event.OnAdDataChangeEvent;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chendj on 2015/7/20.
 */
public class TopADPagerAdapter extends BasePagerAdapter {

    ViewGroup.LayoutParams mImageViewLP = new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    public TopADPagerAdapter(Context mContext) {
        super(mContext);
        BusProvider.getInstance().register(this);
    }

    @Subscribe
    public void onTopADDataChangeEvent(OnAdDataChangeEvent event){
        int[] resources = (int[]) event.getResourceArr();
        if (resources == null || resources.length <= 0){
            setImageUrls(event.getUrls());
        } else {
            setImageResources(resources);
        }
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onDestory() {
        clearViews();
        BusProvider.getInstance().unregister(this);
    }

    @Override
    protected View generateView(Drawable drawable) {
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(mImageViewLP);
        imageView.setBackgroundDrawable(drawable);
        return imageView;
    }

    @Override
    protected View generateView(String url) {
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(mImageViewLP);
        Glide.with(getContext()).load(url).into(imageView);
        return imageView;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
}
