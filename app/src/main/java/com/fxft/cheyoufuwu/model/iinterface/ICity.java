package com.fxft.cheyoufuwu.model.iinterface;

/**
 * Created by chendeji on 26/7/15.
 */
public interface ICity {

    /**
     * 获取城市名称
     * @return
     */
    String getCityName();

    /**
     * 获取城市首字母拼音
     * @return
     */
    String getFirstSpell();

    /**
     * 获取全拼
     * @return
     */
    String getAllSpell();

    /**
     * 获取城市名称
     * @return
     */
    void setCityName(String cityName);

    /**
     * 获取城市首字母拼音
     * @return
     */
    void setFirstSpell(String firstSpell);

    /**
     * 获取全拼
     * @return
     */
    void setAllSpell(String allSpell);

}
