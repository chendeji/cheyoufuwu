package com.fxft.cheyoufuwu.common.interfase;

import com.squareup.otto.Subscribe;

/**
 * 搭配OTTO用于实时监测手机网络状态的变化
 * Created by ChenDJ on 2015/7/22.<br>
 */
public interface OnNetworkStateChanged {

    /**
     * 当网络连接失败
     */
    @Subscribe
    void onNetworkUnable();

    /**
     * 当网络连接成功
     */
    @Subscribe
    void onNetworkEnable();

}
