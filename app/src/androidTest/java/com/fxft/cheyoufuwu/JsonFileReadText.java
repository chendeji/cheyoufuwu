package com.fxft.cheyoufuwu;

import android.test.AndroidTestCase;
import android.text.TextUtils;

import com.fxft.cheyoufuwu.common.util.JsonUtil;
import com.fxft.cheyoufuwu.model.imp.City;

import junit.framework.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by ChenDJ on 2015/7/27.<br>
 */
public class JsonFileReadText extends AndroidTestCase {

    public void testGetJsonFromFile(){
        try {
            InputStream stream = getContext().getAssets().open("areas.json");
            String city_json = JsonUtil.readJsonFile(stream);
            List<City> cities = JsonUtil.parserJsonArray(city_json, City.class);
            Assert.assertNotNull(cities);
            for (City city : cities){
                String cityname = city.getCityName();
                Assert.assertFalse(TextUtils.isEmpty(cityname));
                Assert.assertNotNull(city);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
