package com.fxft.cheyoufuwu.ui.homePage.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.view.CommonTopBar;
import com.fxft.cheyoufuwu.ui.homePage.adapter.CarWashMerchantListAdapter;
import com.fxft.cheyoufuwu.ui.homePage.adapter.PopAdapter;
import com.fxft.cheyoufuwu.ui.homePage.iView.IWashCarView;
import com.fxft.cheyoufuwu.ui.homePage.presenter.CarWashPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarWashActivity extends AppCompatActivity implements IWashCarView, View.OnClickListener {

    //顶部三个选项
    private static final int DISTANCE_SEARCH_MODE = 1;
    private static final int CARTYPE_SEARCH_MODE = 2;
    private static final int SORTTYPE_SEARCH_MODE = 3;

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
    private PopupWindow pop;
    private int popWindowWidth;

    private ListView popView;
    private PopAdapter mModeAdapter;
    private int currentChooseMode;

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
                //TODO 跳到商户详情页面
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
                        intent = new Intent(CarWashActivity.this, SearchMerchantActivity.class);
                        startActivity(intent);
                        break;
                    case SEC_RIGHT_CLICK:
                        //TODO 地图
                        break;
                }
            }
        });
        llDistanceChoose.setOnClickListener(this);
        llCarTypeChoose.setOnClickListener(this);
        llSortTypeChoose.setOnClickListener(this);

        //获取到每个菜单栏的宽度
        llDistanceChoose.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                popWindowWidth = llDistanceChoose.getMeasuredWidth();
                llDistanceChoose.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        popView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mode = (String) parent.getItemAtPosition(position);
                switch (currentChooseMode) {
                    case DISTANCE_SEARCH_MODE:
                        tvDistanceModeName.setText(mode);
                        break;
                    case CARTYPE_SEARCH_MODE:
                        tvCarTypeModeName.setText(mode);
                        break;
                    case SORTTYPE_SEARCH_MODE:
                        tvSortModeName.setText(mode);
                        break;
                }
                if (pop != null && pop.isShowing()){
                    pop.dismiss();
                }
                //执行更新商户列表的操作
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_distance_choose:
                currentChooseMode = DISTANCE_SEARCH_MODE;
                mModeAdapter.setData(distance_mode);
                break;
            case R.id.ll_sort_type_choose:
                currentChooseMode = SORTTYPE_SEARCH_MODE;
                mModeAdapter.setData(sort_mode);
                break;
            case R.id.ll_car_type_choose:
                currentChooseMode = CARTYPE_SEARCH_MODE;
                mModeAdapter.setData(car_type);
                break;
        }

        //显示PopupWindow
        if (pop == null) {
            pop = new PopupWindow(popView, popWindowWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            pop.setBackgroundDrawable(new BitmapDrawable());
        }
        pop.setOutsideTouchable(true);
        if (!pop.isShowing()) {
            pop.showAsDropDown(view, 0, 2);
        } else {
            pop.dismiss();
        }
    }

    private void initComponent() {
        popView = new ListView(this);
        popView.setBackgroundColor(Color.WHITE);
        popView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));
        mModeAdapter = new PopAdapter(this);
        popView.setAdapter(mModeAdapter);

        tvDistanceModeName.setText(distance_mode[0]);
        tvCarTypeModeName.setText(car_type[0]);
        tvSortModeName.setText(sort_mode[0]);

        if (mAdapter == null) {
            mAdapter = new CarWashMerchantListAdapter(this);
        }
        lvMerchantItem.setAdapter(mAdapter);

//        distanceModeLayout = LayoutInflater.from(this).inflate(R.layout.distance_mode_list_layout, null, true);
//        nearMode = distanceModeLayout.findViewById(R.id.tv_near);
//        distance_5km_Mode = distanceModeLayout.findViewById(R.id.tv_distance_5km);
//        distance_10km_Mode = distanceModeLayout.findViewById(R.id.tv_distance_10km);
//
//        carTypeListLayout = LayoutInflater.from(this).inflate(R.layout.car_type_list_layout, null, true);
//        allCarType = carTypeListLayout.findViewById(R.id.tv_all_car_type);
//        carType = carTypeListLayout.findViewById(R.id.tv_car);
//        suvType = carTypeListLayout.findViewById(R.id.tv_SUV);
//        othorType = carTypeListLayout.findViewById(R.id.tv_other);
//
//        sortModeListLayout = LayoutInflater.from(this).inflate(R.layout.sort_mode_list, null, true);
//        priceLowMode = sortModeListLayout.findViewById(R.id.tv_price_low);
//        commentValueHighMode = sortModeListLayout.findViewById(R.id.tv_comment);
//        sellCountBigMode = sortModeListLayout.findViewById(R.id.tv_sell_count);
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        presenter.onDestory();
        mAdapter.onDestory();
        super.onDestroy();
    }

}
