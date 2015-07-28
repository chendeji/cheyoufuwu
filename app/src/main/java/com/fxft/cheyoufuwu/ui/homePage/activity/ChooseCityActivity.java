package com.fxft.cheyoufuwu.ui.homePage.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.view.CommonSearchLayout;
import com.fxft.cheyoufuwu.common.view.CommonTopBar;
import com.fxft.cheyoufuwu.common.view.SideBar;
import com.fxft.cheyoufuwu.model.iinterface.ICity;
import com.fxft.cheyoufuwu.ui.homePage.adapter.SortAdapter;
import com.fxft.cheyoufuwu.ui.homePage.iView.ICityView;
import com.fxft.cheyoufuwu.ui.homePage.presenter.CityPresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChooseCityActivity extends AppCompatActivity implements ICityView {

    @Bind(R.id.ctb_choose_city_top_bar)
    CommonTopBar ctbChooseCityTopBar;
    @Bind(R.id.csl_city_search_layout)
    CommonSearchLayout cslCitySearchLayout;
    @Bind(R.id.ll_current_location_city)
    LinearLayout llCurrentLocationCity;
    @Bind(R.id.lv_city_list)
    ListView lvCityList;
    @Bind(R.id.sb_list_locate_bar)
    SideBar sbListLocateBar;
    @Bind(R.id.tv_location_first_spell)
    TextView tvLocationFirstSpell;
    private SortAdapter mSortAdapter;
    private CityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);
        ButterKnife.bind(this);
        initComponent();
        initEvent();

        //初始化P
        if (presenter == null) {
            presenter = new CityPresenter(this);
        }
        //获取全部的城市数据
        presenter.getCity(null);
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    private void initComponent() {
        if (mSortAdapter == null){
            mSortAdapter = new SortAdapter(this);
        }
        lvCityList.setAdapter(mSortAdapter);
    }

    private void initEvent() {
        ctbChooseCityTopBar.setOnTitleButtonClickListener(new CommonTopBar.OnTitleButtonClickListener() {
            @Override
            public void onTitleButtonCallback(View view, int buttonPos) {
                switch (buttonPos) {
                    case LEFT_CLICK:
                        //点击返回键
                        finish();
                        break;
                }
            }
        });
        cslCitySearchLayout.setKeyWordChangedListener(new CommonSearchLayout.OnSearchKeyWordChanged() {
            @Override
            public void onKeyChanged(String keyWord) {
                presenter.getCity(keyWord);
            }

            @Override
            public void onKeyClear() {
                presenter.getCity(null);
            }
        });
        llCurrentLocationCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        lvCityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        sbListLocateBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                int position = mSortAdapter.getPositionForSection(s.charAt(0));
                if (position != -1){
                    lvCityList.setSelection(position);
                }
            }
        });
        sbListLocateBar.setTextView(tvLocationFirstSpell);
    }


    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        presenter.onDestory();
        super.onDestroy();
    }

    @Override
    public void setCityList(List<ICity> cities) {
        if (cities == null || cities.isEmpty())
            return;
        mSortAdapter.updateListView(cities);
    }
}
