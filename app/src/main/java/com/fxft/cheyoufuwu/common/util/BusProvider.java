package com.fxft.cheyoufuwu.common.util;

import com.squareup.otto.Bus;

/**
 * Created by taosj on 2015/7/17.
 */
public class BusProvider {
    private static Bus ourInstance = new Bus();

    public static Bus getInstance() {
        return ourInstance;
    }

    private BusProvider() {
    }
}
