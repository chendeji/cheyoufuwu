package com.fxft.cheyoufuwu.model.imp;

import com.fxft.cheyoufuwu.model.iinterface.IAutoSense;

import org.joda.time.DateTime;
import java.util.Locale;

/**
 * Created by ChenDJ on 2015/7/23.<br>
 */
public class AutoSense implements IAutoSense {

    private String name;
    private String category;
    private String photoUrl;
    private long updateTime;

    public AutoSense(String name, String photoUrl, String category, long updateTime) {
        this.name = name;
        this.category = category;
        this.updateTime = updateTime;
        this.photoUrl = photoUrl;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPhotoUrl() {
        return this.photoUrl;
    }

    @Override
    public String getCategory() {
        return this.category;
    }

    @Override
    public String getDate() {
        DateTime dateTime = new DateTime(updateTime);
        String updateTimeStr = dateTime.toString("yyyy-MM-dd", Locale.CHINA);
        return updateTimeStr;
    }
}
