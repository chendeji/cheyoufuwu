package com.fxft.cheyoufuwu.network;

/**
 * 接口工厂类，用于管理接口模块
 * Created by chendeji on 27/9/14.
 */
public class AppServerFactory {

    private AppServerFactory(){};
    private static AppServerFactory factory;
    public synchronized static AppServerFactory getFactory(){
        if (factory == null)
            factory = new AppServerFactory();
        return factory;
    }

}
