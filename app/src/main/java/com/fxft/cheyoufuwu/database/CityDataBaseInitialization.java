package com.fxft.cheyoufuwu.database;

import android.content.Context;
import android.text.TextUtils;

import com.fxft.cheyoufuwu.common.util.ChineseSpelling;
import com.fxft.cheyoufuwu.common.util.JsonUtil;
import com.fxft.cheyoufuwu.model.iinterface.ICity;
import com.fxft.cheyoufuwu.model.imp.City;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenDJ on 2015/7/27.<br>
 */
public class CityDataBaseInitialization {
    private static CityDataBaseInitialization ourInstance = new CityDataBaseInitialization();

    public static CityDataBaseInitialization getInstance() {
        return ourInstance;
    }

    private CityDataBaseInitialization() {
    }

    public void init(DbUtils db, Context context) {
        //将城市数据读取
        List<ICity> cities = new ArrayList<>();
        try {
            InputStream stream = context.getAssets().open("areas.json");
            String city_json = JsonUtil.readJsonFile(stream);
            JSONArray array = new JSONArray(city_json);
            ICity city = null;
            for (int i = 0; i < array.length(); i++) {
                //第一层全部是省份数据
                JSONObject object = array.getJSONObject(i);
                JSONArray cityArray = object.getJSONArray("sub");
                if (cityArray.length() <= 0)
                    continue;
                for (int j = 0; j < cityArray.length(); j++) {
                    //第二层城市数据
                    JSONObject cityObject = cityArray.getJSONObject(j);
                    String cityName = cityObject.getString("city");
                    city = new City();
                    city.setCityName(cityName);
                    city.setAllSpell(ChineseSpelling.getSelling(cityName));
                    city.setFirstSpell(ChineseSpelling.getFirstSpelling(cityName));
                    cities.add(city);
                    if (cityObject.has("sub")) {
                        JSONArray areaArray = cityObject.getJSONArray("sub");
                        if (areaArray.length() <= 0)
                            continue;
                        for (int z = 0; z < areaArray.length(); z++) {
                            //第三层市区数据
                            JSONObject areaObject = areaArray.getJSONObject(z);
                            String areaName = areaObject.getString("city");
                            city = new City();
                            city.setCityName(areaName);
                            city.setAllSpell(ChineseSpelling.getSelling(areaName));
                            city.setFirstSpell(ChineseSpelling.getFirstSpelling(areaName));
                            cities.add(city);
                        }
                    }
                }
            }
            //将所有读取的数据存入到数据库中
            db.configAllowTransaction(true);
            db.saveAll(cities);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
