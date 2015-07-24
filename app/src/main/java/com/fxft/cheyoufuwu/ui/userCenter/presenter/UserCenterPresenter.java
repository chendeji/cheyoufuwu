package com.fxft.cheyoufuwu.ui.userCenter.presenter;

import com.fxft.cheyoufuwu.common.interfase.IActivity;
import com.fxft.cheyoufuwu.common.util.BusProvider;
import com.fxft.cheyoufuwu.model.iinterface.IUser;
import com.fxft.cheyoufuwu.model.imp.User;
import com.fxft.cheyoufuwu.ui.userCenter.iView.IUserCenterView;

import java.lang.ref.WeakReference;

/**
 * Created by ChenDJ on 2015/7/24.<br>
 */
public class UserCenterPresenter implements IActivity {

    private WeakReference<IUserCenterView> userCenterView;

    public UserCenterPresenter(IUserCenterView userCenterView) {
        this.userCenterView = new WeakReference<IUserCenterView>(userCenterView);
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestory() {
        BusProvider.getInstance().unregister(this);
        userCenterView = null;
    }

    public void getUserInfo() {
        IUser user = new User("http://i3.dpfile.com/pc/b486cc8fd6910bf1f7a0ac8bf60fda68(278x200)/thumb.jpg", "chendeji", 1, "15060112132", 100);
        userCenterView.get().setUserHeadImage(user.getHeadImageUrl());
        userCenterView.get().setUserNickName(user.getNickName());
        userCenterView.get().setUserVipLevel(user.getLevel());
    }
}
