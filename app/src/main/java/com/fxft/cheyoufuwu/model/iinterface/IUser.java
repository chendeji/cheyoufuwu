package com.fxft.cheyoufuwu.model.iinterface;

/**
 * Created by ChenDJ on 2015/7/24.<br>
 */
public interface IUser {

    /**
     * 获取用户头像
     * @return the head image url
     */
    String getHeadImageUrl();

    /**
     * 获取用户昵称
     *
     * @return the nick name
     */
    String getNickName();

    /**
     * 获取用户VIP等级
     *
     * @return the level
     */
    int getLevel();

    /**
     * 获取用户电话号码
     *
     * @return the phone number
     */
    String getPhoneNumber();

    /**
     * 获取用户成长值
     *
     * @return the growth value
     */
    long getGrowthValue();


}
