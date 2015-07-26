package com.fxft.cheyoufuwu.model.imp;

import com.fxft.cheyoufuwu.database.EntityBase;
import com.fxft.cheyoufuwu.model.iinterface.IUser;

/**
 * Created by ChenDJ on 2015/7/24.<br>
 */
public class User extends EntityBase implements IUser {

    private String headImageUrl;
    private String nickName;
    private int level;
    private String phoneNumber;
    private long growthValue;

    public User(String headImageUrl, String nickName, int level, String phoneNumber, long growthValue) {
        this.headImageUrl = headImageUrl;
        this.nickName = nickName;
        this.level = level;
        this.phoneNumber = phoneNumber;
        this.growthValue = growthValue;
    }

    @Override
    public String getHeadImageUrl() {
        return this.headImageUrl;
    }

    @Override
    public String getNickName() {
        return this.nickName;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public long getGrowthValue() {
        return this.growthValue;
    }
}
