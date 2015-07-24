package com.fxft.cheyoufuwu.model.iinterface;

/**
 * 商户的实体抽象类
 * Created by taosj on 2015/7/20.
 */
public interface IMerchant {

    /**
     * 获取商户的图片
     * @return url路径
     */
    String getImageUrl();

    /**
     * 获取商户的名称
     * @return 商户名称
     */
    String getName();

    /**
     * 商户距离用户定位地点的大概距离
     * @return 距离 精确到小数点两位
     */
    String getDistance();

}
