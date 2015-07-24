package com.fxft.cheyoufuwu.map.iinterface;

import android.content.Context;
import com.fxft.cheyoufuwu.common.interfase.StaticClassReleace;

public interface IMapFactory extends StaticClassReleace {
    public IMap getInstance(Context context);
}
