package com.fxft.cheyoufuwu.ui.common;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;

import com.fxft.cheyoufuwu.common.interfase.IActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenDJ on 2015/7/23.<br>
 */
public abstract class BasePagerAdapter extends PagerAdapter implements IActivity {
    private Context mContext;
    private List<View> drawables = new ArrayList<>();

    protected void clearViews() {
        if (drawables == null || drawables.isEmpty())
            return;
        drawables.clear();
        drawables = null;
    }

    protected Context getContext() {
        return this.mContext;
    }

    public BasePagerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setImageUrls(List<String> urls) {
        if (urls == null || urls.isEmpty())
            return;
        drawables.clear();
        for (String url : urls) {
            View view = generateView(url);
            drawables.add(view);
        }
    }

    public void setImageResources(int[] resource) {
        if (resource == null || resource.length <= 0)
            return;
        drawables.clear();
        Resources r = mContext.getResources();
        for (int i = 0; i < resource.length; i++) {
            Drawable drawable = r.getDrawable(resource[i]);
            View view = generateView(drawable);
            drawables.add(view);
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = drawables.get(position % drawables.size());
        ViewParent parent = view.getParent();
        if (parent != null){
            ((ViewGroup)parent).removeView(view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView(drawables.get(position % drawables.size()));
    }

    @Override
    public int getItemPosition(Object object) {
        if (drawables.size() > 0) {
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

    /**
     * Generate view.
     *
     * @param drawable 图片资源
     * @return the view
     */
    protected abstract View generateView(Drawable drawable);

    /**
     * Generate view.
     *
     * @param url the url
     * @return the view
     */
    protected abstract View generateView(String url);

}
