package com.fxft.cheyoufuwu.ui.descovery.event;

import java.util.List;

/**
 * Created by ChenDJ on 2015/7/23.<br>
 */
public class OnAutoSenseTopAdDataChangeEvent {
    private Object resourceArr;
    private List<String> urls;

    public Object getResourceArr() {
        return resourceArr;
    }

    public List<String> getUrls() {
        return urls;
    }

    public OnAutoSenseTopAdDataChangeEvent(Object resourceArr, List<String> urls) {
        this.resourceArr = resourceArr;
        this.urls = urls;
    }
}
