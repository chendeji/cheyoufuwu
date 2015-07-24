package com.fxft.cheyoufuwu.network;

/**
 * 注入各个模块的接口工厂，单例
 * Created by chendeji on 27/9/14.
 */
public class AppServerConfig {

    private AppServerConfig(){};
    private static AppServerConfig cfg;
    public synchronized static AppServerConfig getInstance(){
        if (cfg == null){
            cfg = new AppServerConfig();
        }
        return cfg;
    }

    private static AppServerFactory appFactory;

    /**
     * 初始化应用的接口工厂
     */
    public void initServerFactory(){

        //应用常量配置导入
        AppConfigFactory factory = AppConfigFactory.getInstance();

        //注入接口工厂
        appFactory = AppServerFactory.getFactory();

    }

    /**
     * 将注入的工厂类全部回收
     */
    public static void destoryFactory(){
        cfg = null;
        if (appFactory != null){
            appFactory = null;
        }

    }

}
