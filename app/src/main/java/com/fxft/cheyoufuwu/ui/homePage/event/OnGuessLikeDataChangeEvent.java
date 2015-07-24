package com.fxft.cheyoufuwu.ui.homePage.event;

import com.fxft.cheyoufuwu.model.iinterface.IMerchandise;

import java.util.List;

/**
 * Created by ChenDJ on 2015/7/22.<br>
 */
public class OnGuessLikeDataChangeEvent {
    private List<IMerchandise> merchandises;

    public List<IMerchandise> getMerchandises() {
        return merchandises;
    }

    public OnGuessLikeDataChangeEvent(List<IMerchandise> merchandises) {
        this.merchandises = merchandises;
    }
}
