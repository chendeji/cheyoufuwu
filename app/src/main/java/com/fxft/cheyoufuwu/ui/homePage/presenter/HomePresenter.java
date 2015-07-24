package com.fxft.cheyoufuwu.ui.homePage.presenter;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.interfase.IActivity;
import com.fxft.cheyoufuwu.common.util.BusProvider;
import com.fxft.cheyoufuwu.model.iinterface.IMerchandise;
import com.fxft.cheyoufuwu.model.iinterface.IMerchant;
import com.fxft.cheyoufuwu.model.imp.Merchandise;
import com.fxft.cheyoufuwu.model.imp.Merchant;
import com.fxft.cheyoufuwu.ui.homePage.event.OnGuessLikeDataChangeEvent;
import com.fxft.cheyoufuwu.ui.homePage.event.OnAdDataChangeEvent;
import com.fxft.cheyoufuwu.ui.homePage.event.OnNearByMerchantDataChangeEvent;
import com.fxft.cheyoufuwu.ui.homePage.iView.IHomeView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chendj on 2015/7/21.
 */
public class HomePresenter implements IActivity{

    /**
     * 使用弱引用，不然有可能会导致内存泄露
     */
    private WeakReference<IHomeView> mHomeView;
    public HomePresenter(IHomeView iView){
        this.mHomeView = new WeakReference<>(iView);
        BusProvider.getInstance().register(this);
    }

    /**
     * 获取顶部广告图片
     */
    public void getADUrls(){
        //测试用数据
        int[] drawableResourceIDs = new int[]{
                R.drawable.testpicture1,
                R.drawable.testpicture2,
                R.drawable.testpicture3
        };
        BusProvider.getInstance().post(new OnAdDataChangeEvent(drawableResourceIDs, null));
    }

    /**
     * 获取附近商家
     */
    public void getNearByMerchantDate(){
        //测试数据
        List<IMerchant> merchants = new ArrayList<>();
        merchants.add(new Merchant("http://i3.dpfile.com/pc/b486cc8fd6910bf1f7a0ac8bf60fda68(278x200)/thumb.jpg","洗车人家",1519));
        merchants.add(new Merchant("http://i1.dpfile.com/pc/5f1a553eee29ce56f53999fa162f71ba(278x200)/thumb.jpg","永塘盛汽车快修快保中心",1519));
        merchants.add(new Merchant("http://i1.dpfile.com/pc/0c7f79091256f2686eb4fcb7e9db5bcf(278x200)/thumb.jpg","凹陷平汽车凹陷修复",1519));
        merchants.add(new Merchant("http://i1.dpfile.com/pc/a2b108d87ccd079a804ebe639cf9b247(278x200)/thumb.jpg","天睿爱汽车",1519));
        merchants.add(new Merchant("http://i3.dpfile.com/pc/a9f75f735e432fe632495154f78783a0(278x200)/thumb.jpg","博湃汽车保养服务中心",1519));
        merchants.add(new Merchant("http://i1.dpfile.com/pc/5f1a553eee29ce56f53999fa162f71ba(278x200)/thumb.jpg","永塘盛长乐路服务中心",1519));
        BusProvider.getInstance().post(new OnNearByMerchantDataChangeEvent(merchants));
    }

    /**
     * 获取猜你喜欢数据
     */
    public void getFavouriteMerchant(){
        //测试数据
        List<IMerchandise> merchandises = new ArrayList<>();
        merchandises.add(new Merchandise("洗车人家",
                "小轿车洗车8折优惠",
                "http://i3.dpfile.com/pc/b486cc8fd6910bf1f7a0ac8bf60fda68(278x200)/thumb.jpg",
                25,38,1519));
        merchandises.add(new Merchandise("永塘盛汽车快修快保中心",
                "小轿车洗车8折优惠",
                "http://i1.dpfile.com/pc/5f1a553eee29ce56f53999fa162f71ba(278x200)/thumb.jpg",
                25,38,1519));
        merchandises.add(new Merchandise("凹陷平汽车凹陷修复",
                "小轿车洗车8折优惠",
                "http://i1.dpfile.com/pc/0c7f79091256f2686eb4fcb7e9db5bcf(278x200)/thumb.jpg",
                25,38,1519));
        merchandises.add(new Merchandise("博湃汽车保养服务中心",
                "小轿车洗车8折优惠",
                "http://i3.dpfile.com/pc/a9f75f735e432fe632495154f78783a0(278x200)/thumb.jpg",
                25,38,1519));
        BusProvider.getInstance().post(new OnGuessLikeDataChangeEvent(merchandises));
    }

    @Override
    public void onPause() {
        //TODO 取消所有子线程的后台操作
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onDestory() {
        mHomeView = null;
        BusProvider.getInstance().unregister(this);
    }
}
