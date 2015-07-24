package com.fxft.cheyoufuwu.model.imp;

import com.fxft.cheyoufuwu.common.util.StringFormatUtil;
import com.fxft.cheyoufuwu.model.iinterface.IMerchant;

/**
 * 商户实体类
 * Created by chendeji on 2015/7/21.
 */
public class Merchant implements IMerchant{

    private String photoUrl;
    private String name;
    private float distance;

    public Merchant(String photoUrl, String name, float distance) {
        this.photoUrl = photoUrl;
        this.name = name;
        this.distance = distance;
    }

    @Override
    public String getImageUrl() {
        return this.photoUrl;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDistance() {
        return StringFormatUtil.afterDecimalTwo(this.distance / 1000);
    }
}
