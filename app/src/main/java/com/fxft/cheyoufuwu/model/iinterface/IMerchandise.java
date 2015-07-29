package com.fxft.cheyoufuwu.model.iinterface;

/**
 * Created by ChenDJ on 2015/7/22.<br>
 */
public interface IMerchandise {

    /**
     * 获取商品名称
     * @return
     */
    String getName();

    /**
     * 获取商品图片
     * @return
     */
    String getMerchantdisePhoto();

    /**
     * 获取商品描述细节
     * @return
     */
    String getDetail();

    /**
     * 商品当前价格
     * @return
     */
    int getCurrentPrice();

    /**
     * 商品往期价格
     * @return
     */
    int getListPrice();

    /**
     * 获取当前用户位置与该商品所在商店的距离
     * @return
     */
    String getDistance();

    /**
     * 获取商品的评分
     * @return
     */
    int getCommentValue();

}
