package com.fxft.cheyoufuwu.common.interfase;

/**
 * 主要用于回收view层的资源
 * Created by chendj on 2015/7/21.
 */
public interface IBaseView {

    void recyclePresenter();
    void recycleAdapter();
}
