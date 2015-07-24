package com.fxft.cheyoufuwu.ui.homePage.event;

import com.fxft.cheyoufuwu.model.iinterface.IMerchant;

import java.util.List;

/**
 * 附近商户数据变化事件
 * Created by chendj on 2015/7/21.
 */
public class OnNearByMerchantDataChangeEvent {

    List<IMerchant> merchants;

    public List<IMerchant> getMerchants() {
        return merchants;
    }
    public OnNearByMerchantDataChangeEvent(List<IMerchant> merchants) {
        this.merchants = merchants;
    }
}
