package com.fxft.cheyoufuwu.ui.mall.event;

import com.fxft.cheyoufuwu.model.iinterface.IMerchandise;

import java.util.List;

/**
 * Created by ChenDJ on 2015/7/22.<br>
 */
public class OnRecommendMerchandiseChangeEvent {
    private List<IMerchandise> mMerchandise;

    public List<IMerchandise> getmMerchandise() {
        return mMerchandise;
    }

    public OnRecommendMerchandiseChangeEvent(List<IMerchandise> merchandises) {
        this.mMerchandise = merchandises;
    }

}
