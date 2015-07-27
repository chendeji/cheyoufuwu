package com.fxft.cheyoufuwu.ui.homePage.presenter;

import android.content.Context;

import com.fxft.cheyoufuwu.common.interfase.IActivity;
import com.fxft.cheyoufuwu.model.iinterface.ICity;
import com.fxft.cheyoufuwu.ui.common.iinterface.UITaskCallBack;
import com.fxft.cheyoufuwu.ui.common.model.ReturnMes;
import com.fxft.cheyoufuwu.ui.homePage.task.ChooseCityTask;
import com.fxft.cheyoufuwu.ui.homePage.iView.ICityView;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by chendeji on 26/7/15.
 */
public class CityPresenter implements IActivity{

    private final Context mContext;
    private WeakReference<ICityView> cityViewWeakReference;

    public CityPresenter(ICityView cityView) {
        this.cityViewWeakReference = new WeakReference<ICityView>(cityView);
        this.mContext = (Context)cityViewWeakReference.get();
    }

    public void getCity(String city){
        //TODO 从数据库获取城市数据
        new ChooseCityTask(mContext, city, new UITaskCallBack<ReturnMes<List<ICity>>>() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPostExecute(ReturnMes<List<ICity>> returnMes) {
                List<ICity> cities = returnMes.object;
                if (cities == null || cities.isEmpty())
                    return;
                cityViewWeakReference.get().setCityList(cities);
            }

            @Override
            public void onExecuteError(String errorMsg) {
//                ToastUtil.showLongToast(mContext, errorMsg);
            }
        }).excuteProxy((Void[])null);
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
