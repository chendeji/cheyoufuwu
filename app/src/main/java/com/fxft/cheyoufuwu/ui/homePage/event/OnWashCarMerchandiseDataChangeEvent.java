package com.fxft.cheyoufuwu.ui.homePage.event;

import com.fxft.cheyoufuwu.model.iinterface.IMerchandise;

import java.util.List;

/**
 * Created by ChenDJ on 2015/7/28.<br>
 */
public class OnWashCarMerchandiseDataChangeEvent {
    private boolean isRefreshData;
    private List<IMerchandise> merchandises;

    public boolean isRefreshData() {
        return isRefreshData;
    }
    public List<IMerchandise> getMerchandises() {
        return merchandises;
    }

    public OnWashCarMerchandiseDataChangeEvent(List<IMerchandise> merchandises, boolean isRefreshData) {
        this.merchandises = merchandises;
        this.isRefreshData = isRefreshData;
    }
}
