package com.fxft.cheyoufuwu.ui.homePage.event;

import java.util.List;

/**
 * 广告数据事件
 * Created by chendj on 2015/7/21.
 */
public class OnAdDataChangeEvent {

    private Object resourceArr;
    private List<String> urls;

    public Object getResourceArr() {
        return resourceArr;
    }

    public List<String> getUrls() {
        return urls;
    }

    public OnAdDataChangeEvent(Object resourceArr, List<String> urls) {
        this.resourceArr = resourceArr;
        this.urls = urls;
    }
}
