package com.fxft.cheyoufuwu.database;

import android.content.Context;

import com.fxft.cheyoufuwu.model.imp.City;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

/**
 * Created by ChenDJ on 2015/7/27.<br>
 */
public class DatabaseManager implements DbUtils.DbUpgradeListener {
    private static DatabaseManager ourInstance = new DatabaseManager();
    private DbUtils db;

    public static DatabaseManager getInstance() {
        return ourInstance;
    }

    private DatabaseManager() {
    }

    public void initialization(Context context, String dbName, int dbVersion) throws DbException {
        //创建数据库
        DbUtils.DaoConfig config = new DbUtils.DaoConfig(context);
        config.setDbName(dbName);
        config.setDbVersion(dbVersion);
        config.setDbUpgradeListener(this);
        db = DbUtils.create(config);

        //是否需要初始化一下城市表
        boolean cityExist = db.tableIsExist(City.class);
        if (!cityExist) {
            CityDataBaseInitialization.getInstance().init(db, context);
        }
    }

    /**
     * 获取数据库操作对象
     * @return DbUtils
     */
    public DbUtils getDb() {
        return db;
    }

    @Override
    public void onUpgrade(DbUtils dbUtils, int i, int i1) {

    }
}
