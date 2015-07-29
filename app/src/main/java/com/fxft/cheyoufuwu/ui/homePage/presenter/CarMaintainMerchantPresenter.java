package com.fxft.cheyoufuwu.ui.homePage.presenter;

import com.fxft.cheyoufuwu.common.interfase.IActivity;
import com.fxft.cheyoufuwu.common.util.BusProvider;
import com.fxft.cheyoufuwu.model.iinterface.IMerchant;
import com.fxft.cheyoufuwu.model.imp.Merchant;
import com.fxft.cheyoufuwu.ui.homePage.event.OnCarMaintainListDataChangeEvent;
import com.fxft.cheyoufuwu.ui.homePage.iView.ICarMaintainView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenDJ on 2015/7/29.<br>
 */
public class CarMaintainMerchantPresenter implements IActivity {

    private WeakReference<ICarMaintainView> reference;

    public CarMaintainMerchantPresenter(ICarMaintainView view) {
        this.reference = new WeakReference<ICarMaintainView>(view);
        BusProvider.getInstance().register(this);
    }

    public void getCarMaintainMerchantList() {
        List<IMerchant> merchants = new ArrayList<>();
        merchants.add(new Merchant("http://i3.dpfile.com/pc/b486cc8fd6910bf1f7a0ac8bf60fda68(278x200)/thumb.jpg",
                "洗车人家",
                1519,
                0,
                "台江区",
                4.5f,
                0,
                true,false,true,false,true));
        merchants.add(new Merchant("http://i1.dpfile.com/pc/5f1a553eee29ce56f53999fa162f71ba(278x200)/thumb.jpg",
                "永塘盛汽车快修快保中心",
                1519,
                0,
                "台江区",
                4.5f,
                0,
                true,false,true,false,true));
        merchants.add(new Merchant("http://i1.dpfile.com/pc/0c7f79091256f2686eb4fcb7e9db5bcf(278x200)/thumb.jpg",
                "凹陷平汽车凹陷修复",
                1519,
                0,
                "台江区",
                4.5f,
                0,
                true,false,true,false,true));
        merchants.add(new Merchant("http://i1.dpfile.com/pc/a2b108d87ccd079a804ebe639cf9b247(278x200)/thumb.jpg",
                "天睿爱汽车",
                1519,
                0,
                "台江区",
                4.5f,
                0,
                true,false,true,false,true));
        merchants.add(new Merchant("http://i3.dpfile.com/pc/a9f75f735e432fe632495154f78783a0(278x200)/thumb.jpg",
                "博湃汽车保养服务中心",
                1519,
                0,
                "台江区",
                4.5f,
                0,
                true,false,true,false,true));
        merchants.add(new Merchant("http://i1.dpfile.com/pc/5f1a553eee29ce56f53999fa162f71ba(278x200)/thumb.jpg",
                "永塘盛长乐路服务中心",
                1519,
                0,
                "台江区",
                4.5f,
                0,
                true,false,true,false,true));

        BusProvider.getInstance().post(new OnCarMaintainListDataChangeEvent(merchants, true));
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestory() {
        reference.clear();
        reference = null;
        BusProvider.getInstance().unregister(this);
    }
}
