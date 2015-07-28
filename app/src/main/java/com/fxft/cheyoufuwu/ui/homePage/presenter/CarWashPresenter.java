package com.fxft.cheyoufuwu.ui.homePage.presenter;

import com.fxft.cheyoufuwu.common.interfase.IActivity;
import com.fxft.cheyoufuwu.common.util.BusProvider;
import com.fxft.cheyoufuwu.model.iinterface.IMerchandise;
import com.fxft.cheyoufuwu.model.imp.Merchandise;
import com.fxft.cheyoufuwu.ui.homePage.event.OnWashCarMerchandiseDataChangeEvent;
import com.fxft.cheyoufuwu.ui.homePage.iView.IWashCarView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenDJ on 2015/7/28.<br>
 */
public class CarWashPresenter implements IActivity {

    private WeakReference<IWashCarView> washCarViewWeakReference;

    public CarWashPresenter(IWashCarView washCarView) {
        this.washCarViewWeakReference = new WeakReference<IWashCarView>(washCarView);
        BusProvider.getInstance().register(this);
    }

    public void getWashCarMerchandise(boolean isRefreshData){
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
        BusProvider.getInstance().post(new OnWashCarMerchandiseDataChangeEvent(merchandises, isRefreshData));
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestory() {
        washCarViewWeakReference.clear();
        washCarViewWeakReference = null;
        BusProvider.getInstance().unregister(this);
    }
}
