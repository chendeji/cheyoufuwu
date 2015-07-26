package com.fxft.cheyoufuwu.model.imp;

import com.fxft.cheyoufuwu.common.util.StringFormatUtil;
import com.fxft.cheyoufuwu.model.iinterface.IMerchandise;

/**
 * 商品类
 * Created by ChenDJ on 2015/7/22.<br>
 */
public class Merchandise implements IMerchandise {

    private String name;
    private String detail;
    private String photoUrl;
    private int currentPrice;
    private int listPrice;
    private float distance;

    public Merchandise(String name, String detail, String photoUrl, int currentPrice, int listPrice, float distance) {
        this.name = name;
        this.detail = detail;
        this.photoUrl = photoUrl;
        this.currentPrice = currentPrice;
        this.listPrice = listPrice;
        this.distance = distance;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getMerchantdisePhoto() {
        return this.photoUrl;
    }

    @Override
    public String getDetail() {
        return this.detail;
    }

    @Override
    public int getCurrentPrice() {
        return this.currentPrice;
    }

    @Override
    public int getListPrice() {
        return this.listPrice;
    }

    @Override
    public String getDistance() {
        return StringFormatUtil.afterDecimalTwo(this.distance / 1000);
    }
}
