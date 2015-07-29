package com.fxft.cheyoufuwu.model.imp;

import com.fxft.cheyoufuwu.common.util.StringFormatUtil;
import com.fxft.cheyoufuwu.model.iinterface.IMerchant;

/**
 * 商户实体类
 * Created by chendeji on 2015/7/21.
 */
public class Merchant implements IMerchant {

    private String photoUrl;
    private String name;
    private float distance;

    //------------版本3
    private int sellCount;
    private String area;
    private float rating;
    private int serviceType = COMPOSITE_SERVICE;    //默认值为综合服务
    private boolean hasSnacksService;
    private boolean hasWIFIService;
    private boolean hasLightService;
    private boolean hasVideoService;
    private boolean hasTeeService;

    public Merchant(String photoUrl, String name, float distance) {
        this.photoUrl = photoUrl;
        this.name = name;
        this.distance = distance;
    }

    public Merchant(String photoUrl, String name, float distance, int sellCount, String area,
                    float rating, int serviceType, boolean hasSnacksService, boolean hasWIFIService,
                    boolean hasLightService, boolean hasVideoService, boolean hasTeeService) {
        this.photoUrl = photoUrl;
        this.name = name;
        this.distance = distance;
        this.sellCount = sellCount;
        this.area = area;
        this.rating = rating;
        this.serviceType = serviceType;
        this.hasSnacksService = hasSnacksService;
        this.hasWIFIService = hasWIFIService;
        this.hasLightService = hasLightService;
        this.hasVideoService = hasVideoService;
        this.hasTeeService = hasTeeService;
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

    @Override
    public int getSellCount() {
        return this.sellCount;
    }

    @Override
    public String getArea() {
        return this.area;
    }

    @Override
    public float getRating() {
        return this.rating;
    }

    @Override
    public int getServiceType() {
        return this.serviceType;
    }

    @Override
    public boolean hasWifiService() {
        return this.hasWIFIService;
    }

    @Override
    public boolean hasSnacksService() {
        return this.hasSnacksService;
    }

    @Override
    public boolean hasLightService() {
        return this.hasLightService;
    }

    @Override
    public boolean hasVideoService() {
        return this.hasVideoService;
    }

    @Override
    public boolean hasTeeService() {
        return this.hasTeeService;
    }
}
