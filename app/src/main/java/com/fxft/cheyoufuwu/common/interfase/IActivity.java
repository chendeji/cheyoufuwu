package com.fxft.cheyoufuwu.common.interfase;

/**
 * 用于普通类能够模拟Activity的生命周期
 * 主要使用与普通对象有绑定订阅事件的解绑<br>
 * Created by chendj on 2015/7/21.
 */
public interface IActivity {

    void onPause();
    void onResume();
    void onDestory();
}
