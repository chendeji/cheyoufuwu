package com.fxft.cheyoufuwu.ui.homePage.event;

import com.fxft.cheyoufuwu.model.iinterface.IMerchant;

import java.util.List;

/**
 * Created by ChenDJ on 2015/7/29.<br>
 */
public class OnCarMaintainListDataChangeEvent {
    List<IMerchant> merchants;
    boolean isRefresh;

    public List<IMerchant> getMerchants() {
        return merchants;
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public OnCarMaintainListDataChangeEvent(List<IMerchant> merchants, boolean isRefresh) {
        this.merchants = merchants;
        this.isRefresh = isRefresh;
    }
}
