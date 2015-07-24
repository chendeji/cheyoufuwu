package com.fxft.cheyoufuwu.ui.descovery.event;

import com.fxft.cheyoufuwu.model.iinterface.IAutoSense;

import java.util.List;

/**
 * Created by ChenDJ on 2015/7/23.<br>
 */
public class OnAutoSenseListDataChangeEvent {

    private List<IAutoSense> autoSenses;

    public List<IAutoSense> getAutoSenses() {
        return autoSenses;
    }

    public OnAutoSenseListDataChangeEvent(List<IAutoSense> autoSenses) {
        this.autoSenses = autoSenses;
    }
}
