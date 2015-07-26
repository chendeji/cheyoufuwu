package com.fxft.cheyoufuwu.ui.homePage.presenter;

import com.fxft.cheyoufuwu.common.interfase.IActivity;
import com.fxft.cheyoufuwu.ui.homePage.iView.ICityView;

import java.lang.ref.WeakReference;

/**
 * Created by chendeji on 26/7/15.
 */
public class CityPresenter implements IActivity{

    private WeakReference<ICityView> cityViewWeakReference;

    public CityPresenter(ICityView cityView) {
        this.cityViewWeakReference = new WeakReference<ICityView>(cityView);
    }

    public void getCity(String city){
        //TODO 从数据库获取城市数据
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestory() {
        cityViewWeakReference.clear();
        cityViewWeakReference = null;
    }
}
