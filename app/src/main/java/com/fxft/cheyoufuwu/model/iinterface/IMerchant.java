package com.fxft.cheyoufuwu.model.iinterface;

/**
 * 商户的实体抽象类
 * Created by taosj on 2015/7/20.
 */
public interface IMerchant {

    int COMPOSITE_SERVICE = 0x0;

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

    /**
     * 获取销量
     * @return
     */
    int getSellCount();

    /**
     * 获取商家所在区域
     * @return
     */
    String getArea();

    /**
     * 获取星级评分
     * @return
     */
    float getRating();

    /**
     * 获取商家提供服务类型
     * @return
     */
    int getServiceType();

    /**
     * 是否有提供WIFI
     * @return
     */
    boolean hasWifiService();

    /**
     * 是否有提供小吃
     * @return
     */
    boolean hasSnacksService();

    /**
     *
     * @return
     */
    boolean hasLightService();

    /**
     * 是否提供洗车视频记录
     * @return
     */
    boolean hasVideoService();

    /**
     * 是否提供饮料
     * @return
     */
    boolean hasTeeService();

}
