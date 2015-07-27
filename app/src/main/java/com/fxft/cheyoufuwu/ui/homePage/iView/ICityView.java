package com.fxft.cheyoufuwu.ui.homePage.iView;

import com.fxft.cheyoufuwu.model.iinterface.ICity;

import java.util.List;

/**
 * Created by chendeji on 26/7/15.
 */
public interface ICityView {
    /**
     * 设置城市列表
     * @param cities 城市列表
     */
    void setCityList(List<ICity> cities);
}
