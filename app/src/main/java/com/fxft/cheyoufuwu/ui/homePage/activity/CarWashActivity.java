package com.fxft.cheyoufuwu.ui.homePage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.view.CommonTopBar;
import com.fxft.cheyoufuwu.ui.homePage.adapter.CarWashMerchantListAdapter;
import com.fxft.cheyoufuwu.ui.homePage.iView.IWashCarView;
import com.fxft.cheyoufuwu.ui.homePage.presenter.CarWashPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarWashActivity extends AppCompatActivity implements IWashCarView {

    @Bind(R.id.ctb_car_wash_top_bar)
    CommonTopBar ctbCarWashTopBar;
    @Bind(R.id.tv_distance_mode_name)
    TextView tvDistanceModeName;
    @Bind(R.id.ll_distance_choose)
    LinearLayout llDistanceChoose;
    @Bind(R.id.tv_car_type_mode_name)
    TextView tvCarTypeModeName;
    @Bind(R.id.ll_car_type_choose)
    LinearLayout llCarTypeChoose;
    @Bind(R.id.tv_sort_mode_name)
    TextView tvSortModeName;
    @Bind(R.id.ll_sort_type_choose)
    LinearLayout llSortTypeChoose;
    @Bind(R.id.lv_merchant_item)
    ListView lvMerchantItem;

    //顶部的三个选择菜单栏中的选项
    private String[] distance_mode;
    private String[] car_type;
    private String[] sort_mode;
    private CarWashMerchantListAdapter mAdapter;
    private CarWashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_wash);
        ButterKnife.bind(this);

        distance_mode = getResources().getStringArray(R.array.distance_mode);
        car_type = getResources().getStringArray(R.array.car_type);
        sort_mode = getResources().getStringArray(R.array.sort_type);
        initComponent();
        initEvent();

        presenter = new CarWashPresenter(this);
        presenter.getWashCarMerchandise(true);
    }

    private void initEvent() {
        lvMerchantItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        ctbCarWashTopBar.setOnTitleButtonClickListener(new CommonTopBar.OnTitleButtonClickListener() {
            @Override
            public void onTitleButtonCallback(View view, int buttonPos) {
                Intent intent = null;
                switch (buttonPos) {
                    case LEFT_CLICK:
                        finish();
                        break;
                    case FIRST_RIGHT_CLICK:
                        //TODO 搜索
                        intent = new Intent(CarWashActivity.this, SearchMerchantActivity.class);
                        break;
                    case SEC_RIGHT_CLICK:
                        //TODO 地图
                        break;
                }
                startActivity(intent);
            }
        });
    }

    @OnClick(value = {R.id.ll_distance_choose, R.id.tv_sort_mode_name, R.id.ll_car_type_choose})
    public void onModeButtonClick(View view) {
        switch (view.getId()){
            case R.id.ll_distance_choose:
                break;
            case R.id.tv_sort_mode_name:
                break;
            case R.id.ll_car_type_choose:
                break;
        }
    }

    private void initComponent() {
        tvDistanceModeName.setText(distance_mode[0]);
        tvCarTypeModeName.setText(car_type[0]);
        tvSortModeName.setText(sort_mode[0]);

        if (mAdapter == null) {
            mAdapter = new CarWashMerchantListAdapter(this);
        }
        lvMerchantItem.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        presenter.onDestory();
        mAdapter.onDestory();
        super.onDestroy();
    }
}
