package com.fxft.cheyoufuwu.ui.userCenter.event;

import android.graphics.Bitmap;

/**
 * Created by ChenDJ on 2015/7/24.<br>
 */
public class OnUserHeadImageChangeEvent {
    private Bitmap photo;

    public Bitmap getPhoto() {
        return photo;
    }

    public OnUserHeadImageChangeEvent(Bitmap photo) {
        this.photo = photo;
    }
}
