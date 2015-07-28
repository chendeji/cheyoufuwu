package com.fxft.cheyoufuwu.ui.homePage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.view.CommonTopBar;
import com.fxft.cheyoufuwu.ui.homePage.adapter.RefuelStationListAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CarRefuelActivity extends AppCompatActivity {

    @Bind(R.id.ctb_car_wash_top_bar)
    CommonTopBar ctbCarWashTopBar;
    @Bind(R.id.ll_add_rebate)
    LinearLayout llAddRebate;
    @Bind(R.id.tv_more_refuel_station)
    TextView tvMoreRefuelStation;
    @Bind(R.id.tv_empty_text)
    TextView tvEmptyText;
    @Bind(R.id.ll_empty_layout)
    LinearLayout llEmptyLayout;
    @Bind(R.id.lv_refuel_station_list)
    ListView lvRefuelStationList;
    private RefuelStationListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_refuel);
        ButterKnife.bind(this);

        initComponent();
        initEvent();
    }

    private void initComponent() {
        tvEmptyText.setText(getResources().getString(R.string.nomore_refuel_station_nearly));
        mAdapter = new RefuelStationListAdapter(this);
        lvRefuelStationList.setAdapter(mAdapter);

        if (mAdapter.getCount() == 0){
            llEmptyLayout.setVisibility(View.VISIBLE);
            lvRefuelStationList.setVisibility(View.GONE);
        } else {
            llEmptyLayout.setVisibility(View.GONE);
            lvRefuelStationList.setVisibility(View.VISIBLE);
        }
    }

    private void initEvent() {
        ctbCarWashTopBar.setOnTitleButtonClickListener(new CommonTopBar.OnTitleButtonClickListener() {
            @Override
            public void onTitleButtonCallback(View view, int buttonPos) {
                finish();
            }
        });
        llAddRebate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarRefuelActivity.this, BindingRefuelCardActiivty.class);
                startActivity(intent);
            }
        });
        tvMoreRefuelStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        ButterKnife.bind(this);
        super.onDestroy();
    }
}
