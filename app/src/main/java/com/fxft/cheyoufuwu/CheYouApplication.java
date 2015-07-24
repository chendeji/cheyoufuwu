package com.fxft.cheyoufuwu;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by ChenDJ on 2015/7/23.<br>
 */
public class CheYouApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
    }
}
