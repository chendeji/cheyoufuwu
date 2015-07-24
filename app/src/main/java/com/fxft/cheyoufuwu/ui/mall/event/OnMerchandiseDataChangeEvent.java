package com.fxft.cheyoufuwu.ui.mall.event;

import com.fxft.cheyoufuwu.model.iinterface.IMerchandise;

import java.util.List;

/**
 * Created by ChenDJ on 2015/7/22.<br>
 */
public class OnMerchandiseDataChangeEvent {
    private List<IMerchandise> merchandises;

    public List<IMerchandise> getMerchandises() {
        return merchandises;
    }

    public OnMerchandiseDataChangeEvent(List<IMerchandise> merchandises) {
        this.merchandises = merchandises;
    }
}
