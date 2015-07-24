package com.fxft.cheyoufuwu.ui.userCenter.iView;

/**
 * Created by ChenDJ on 2015/7/24.<br>
 */
public interface IUserCenterView {

    /**
     * 设置用户头像
     *
     * @param url the url
     */
    void setUserHeadImage(String url);

    /**
     * 设置用户昵称
     *
     * @param nickName the nick name
     */
    void setUserNickName(String nickName);

    /**
     * 设置用户VIP等级
     *
     * @param level the level
     */
    void setUserVipLevel(int level);


}
