package com.fxft.cheyoufuwu.ui.homePage.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.interfase.IActivity;
import com.fxft.cheyoufuwu.common.util.BusProvider;
import com.fxft.cheyoufuwu.model.iinterface.IMerchant;
import com.fxft.cheyoufuwu.ui.homePage.event.OnNearByMerchantDataChangeEvent;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taosj on 2015/7/20.
 */
public class NearByMerchantAdapter extends PagerAdapter implements IActivity {

    private final Context mContext;
    private final int DEFUALT_PAGE_COUNT = 2;
    private final LayoutInflater mLayoutInflater;
    private int mPage_Count;
    private final int DEFUALT_PAGE_ITEM_COUNT = 3;

    private List<IMerchant> mMerchants = new ArrayList<>();

    public NearByMerchantAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        BusProvider.getInstance().register(this);
    }

    @Subscribe
    public void onReceiveDataChangeEvent(OnNearByMerchantDataChangeEvent event) {
        List<IMerchant> merchants = event.getMerchants();
        if (merchants == null || merchants.isEmpty())
            return;
        setMerchants(merchants);
    }

    private void setMerchants(List<IMerchant> merchants) {
        mMerchants.clear();
        mMerchants.addAll(merchants);
        notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (mMerchants == null || mMerchants.isEmpty())
            return null;
        int startPosition = position * DEFUALT_PAGE_ITEM_COUNT;
        NearByMercahntPagerItem item = new NearByMercahntPagerItem(mContext);
        IMerchant item1 = mMerchants.get(startPosition);
        IMerchant item2 = mMerchants.get(startPosition + 1);
        IMerchant item3 = mMerchants.get(startPosition + 2);
        //第一个item
        if (item1 != null) {
            Glide.with(mContext).load(item1.getImageUrl()).into(item.itemImage1);
            item.itemTitile1.setText(item1.getName());
            item.itemSubTitile1.setText(String.valueOf(item1.getDistance()) + "km");
        }

        if (item2 != null) {
            //第二个item
            Glide.with(mContext).load(item2.getImageUrl()).into(item.itemImage2);
            item.itemTitile2.setText(item2.getName());
            item.itemSubTitile2.setText(String.valueOf(item2.getDistance()) + "km");
        }

        if (item3 != null) {
            //第三个item
            Glide.with(mContext).load(item3.getImageUrl()).into(item.itemImage3);
            item.itemTitile3.setText(item3.getName());
            item.itemSubTitile3.setText(String.valueOf(item3.getDistance()) + "km");
        }
        container.addView(item);
        return item;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public int getCount() {
        return DEFUALT_PAGE_COUNT;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestory() {
        mMerchants.clear();
        mMerchants = null;
        BusProvider.getInstance().unregister(this);
    }

    class NearByMercahntPagerItem extends LinearLayout {

        private ImageView itemImage1;
        private TextView itemTitile1;
        private TextView itemSubTitile1;
        private ImageView itemImage2;
        private TextView itemTitile2;
        private TextView itemSubTitile2;
        private ImageView itemImage3;
        private TextView itemTitile3;
        private TextView itemSubTitile3;


        public NearByMercahntPagerItem(Context context) {
            this(context, null);
        }

        public NearByMercahntPagerItem(Context context, AttributeSet attrs) {
            super(context, attrs);
            init(context, attrs);
        }

        private void init(Context context, AttributeSet attrs) {
            View viewContainer = LayoutInflater.from(context).inflate(R.layout.home_near_shop_item, this, true);
            setOrientation(HORIZONTAL);
            setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            itemImage1 = (ImageView) viewContainer.findViewById(R.id.iv_merchant_image_1);
            itemTitile1 = (TextView) viewContainer.findViewById(R.id.tv_merchant_name_1);
            itemSubTitile1 = (TextView) viewContainer.findViewById(R.id.tv_merchant_distance_1);
            itemImage2 = (ImageView) viewContainer.findViewById(R.id.iv_merchant_image_2);
            itemTitile2 = (TextView) viewContainer.findViewById(R.id.tv_merchant_name_2);
            itemSubTitile2 = (TextView) viewContainer.findViewById(R.id.tv_merchant_distance_2);
            itemImage3 = (ImageView) viewContainer.findViewById(R.id.iv_merchant_image_3);
            itemTitile3 = (TextView) viewContainer.findViewById(R.id.tv_merchant_name_3);
            itemSubTitile3 = (TextView) viewContainer.findViewById(R.id.tv_merchant_distance_3);
        }
    }
}
