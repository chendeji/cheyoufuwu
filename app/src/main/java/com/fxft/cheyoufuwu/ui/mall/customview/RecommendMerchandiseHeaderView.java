package com.fxft.cheyoufuwu.ui.mall.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.util.BusProvider;
import com.fxft.cheyoufuwu.model.iinterface.IMerchandise;
import com.fxft.cheyoufuwu.ui.mall.event.OnRecommendMerchandiseChangeEvent;
import com.squareup.otto.Subscribe;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ChenDJ on 2015/7/22.<br>
 */
public class RecommendMerchandiseHeaderView extends LinearLayout {
    @Bind(R.id.mall_recommend_merchandise_1)
    RecommendMerchandiseItem mallRecommendMerchandise1;
    @Bind(R.id.mall_recommend_merchandise_2)
    RecommendMerchandiseItem mallRecommendMerchandise2;

    public RecommendMerchandiseHeaderView(Context context) {
        this(context, null);
    }

    public RecommendMerchandiseHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Subscribe
    public void onRecommendMerchandiseDataChangeEvent(OnRecommendMerchandiseChangeEvent event){
        List<IMerchandise> merchandiseList = event.getmMerchandise();
        mallRecommendMerchandise1.setComponentValue(merchandiseList.get(0));
        mallRecommendMerchandise2.setComponentValue(merchandiseList.get(1));
    }

    @Override
    protected void onDetachedFromWindow() {
        ButterKnife.unbind(this);
        BusProvider.getInstance().unregister(this);
        super.onDetachedFromWindow();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.mall_merchandise_list_header_view, this, true);
        ButterKnife.bind(this);
        BusProvider.getInstance().register(this);
    }
}
