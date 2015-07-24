package com.fxft.cheyoufuwu.map.imp;

import android.content.Context;

import com.fxft.cheyoufuwu.map.iinterface.IMap;
import com.fxft.cheyoufuwu.map.iinterface.IMapFactory;


/**
 * The type Gaode map factory.
 */
public class GaodeMapFactory implements IMapFactory {
    private static IMapFactory factory;
    private GaodeMapFactory(){}
    public static IMapFactory getFactory(){
        if (factory == null)
            factory = new GaodeMapFactory();
        return factory;
    }

    @Override
    public IMap getInstance(Context context) {
        //TODO 地图实体功能类
        return null;
    }

    @Override
    public void release() {
        //TODO 释放地图实体类引用
    }
}
