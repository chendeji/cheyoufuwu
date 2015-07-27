package com.fxft.cheyoufuwu.model.imp;

import com.fxft.cheyoufuwu.database.EntityBase;
import com.fxft.cheyoufuwu.database.table.CityTable;
import com.fxft.cheyoufuwu.model.iinterface.ICity;
import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by ChenDJ on 2015/7/27.<br>
 */
@Table(name = "city")
public class City extends EntityBase implements ICity {

    @Column(column = CityTable.CITY_NAME, defaultValue = "")
    private String cityName;

    @Column(column = CityTable.CITY_FIRST_SPELL, defaultValue = "")
    private String firstSpell;

    @Column(column = CityTable.CITY_ALL_SPELL, defaultValue = "")
    private String allSpell;

    @Override
    public String getCityName() {
        return cityName;
    }

    @Override
    public String getFirstSpell() {
        return firstSpell;
    }

    @Override
    public String getAllSpell() {
        return allSpell;
    }

    @Override
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public void setFirstSpell(String firstSpell) {
        this.firstSpell = firstSpell;
    }

    @Override
    public void setAllSpell(String allSpell) {
        this.allSpell = allSpell;
    }


}
