package com.fxft.cheyoufuwu.model.iinterface;

/**
 * Created by ChenDJ on 2015/7/23.<br>
 */
public interface IAutoSense {

    /**
     * 获取名称
     * @return
     */
    String getName();

    /**
     * 获取汽车常识的图片
     * @return
     */
    String getPhotoUrl();

    /**
     * 获取常识类型
     * @return
     */
    String getCategory();

    /**
     * 获取时间
     * @return
     */
    String getDate();
}
