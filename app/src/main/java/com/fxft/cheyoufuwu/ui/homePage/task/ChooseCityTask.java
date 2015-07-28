package com.fxft.cheyoufuwu.ui.homePage.task;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.fxft.cheyoufuwu.common.consts.AppConst;
import com.fxft.cheyoufuwu.common.util.ChineseSpelling;
import com.fxft.cheyoufuwu.database.DatabaseManager;
import com.fxft.cheyoufuwu.database.table.CityTable;
import com.fxft.cheyoufuwu.model.iinterface.ICity;
import com.fxft.cheyoufuwu.model.imp.City;
import com.fxft.cheyoufuwu.ui.common.iinterface.UITaskCallBack;
import com.fxft.cheyoufuwu.ui.common.model.ReturnMes;
import com.fxft.cheyoufuwu.ui.common.task.BaseUITask;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenDJ on 2015/7/27.<br>
 */
public class ChooseCityTask extends BaseUITask<Void, Void, ReturnMes<List<ICity>>> {
    private final String mCityName;

    /**
     * 基础的UI界面后台线程
     *
     * @param context      上下文句柄
     * @param city
     * @param taskCallBack UI界面回调
     */
    public ChooseCityTask(Context context, String city, UITaskCallBack<ReturnMes<List<ICity>>> taskCallBack) {
        super(context, taskCallBack, true);
        this.mCityName = city;
    }

    @Override
    protected void fromDBDataSuccess(ReturnMes<List<ICity>> listReturnMes) {
        if (listReturnMes == null
                || listReturnMes.status == AppConst.ERROR
                || listReturnMes.object == null)
            return;
        mTaskCallBack.onPostExecute(listReturnMes);
    }

    @Override
    protected void fromNetWorkDataSuccess(ReturnMes<List<ICity>> listReturnMes) {

    }

    @Override
    protected void fromDBDataError(String errorMsg) {
        Log.i(TAG, errorMsg);
    }

    @Override
    protected void fromNetWorkDataError(String errorMsg) {
    }

    @Override
    protected ReturnMes<List<ICity>> getDataFromNetwork() throws Exception {
        return null;
    }

    @Override
    protected ReturnMes<List<ICity>> getDataFromDB() throws Exception {
        DbUtils db = DatabaseManager.getInstance().getDb();
        List<ICity> result = new ArrayList<>();
        result.clear();
        List<ICity> cities;
        if (TextUtils.isEmpty(mCityName)) {
            cities = db.findAll(
                    Selector.from(City.class)
                            .orderBy(CityTable.CITY_FIRST_SPELL));
            result.addAll(cities);
        } else {
            String firstSpelling = ChineseSpelling.getFirstSpelling(mCityName);
            Log.i(TAG, "firstSpelling:"+firstSpelling);

            String allSpelling = ChineseSpelling.getSelling(mCityName);
            Log.i(TAG, "allSpelling:"+allSpelling);

            cities = db.findAll(
                    Selector.from(City.class)
                            .where(WhereBuilder.b().expr(CityTable.CITY_NAME + " LIKE '%" + mCityName + "%'"))
                            .or(WhereBuilder.b().expr(CityTable.CITY_ALL_SPELL + " LIKE '%" + allSpelling + "%'"))
                            .or(WhereBuilder.b().expr(CityTable.CITY_FIRST_SPELL + " LIKE '%" + firstSpelling + "%'"))
                            .orderBy(CityTable.CITY_FIRST_SPELL));

            for (ICity city : cities){
                if (city.getFirstSpell().startsWith(firstSpelling)
                        || city.getAllSpell().startsWith(allSpelling)) {
                    result.add(city);
                }
            }
        }

        if (result == null || result.isEmpty())
            return null;
        ReturnMes<List<ICity>> returnMes = new ReturnMes<>();
        returnMes.status = AppConst.OK;
        returnMes.object = result;
        return returnMes;
    }
}
