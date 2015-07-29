package com.fxft.cheyoufuwu.ui.homePage.activity;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.view.CommonTopBar;
import com.fxft.cheyoufuwu.ui.homePage.adapter.CarMaintainMerchantListAdapter;
import com.fxft.cheyoufuwu.ui.homePage.adapter.PopAdapter;
import com.fxft.cheyoufuwu.ui.homePage.iView.ICarMaintainView;
import com.fxft.cheyoufuwu.ui.homePage.presenter.CarMaintainMerchantPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarMaintainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ICarMaintainView {

    //顶部三个选项
    private static final int DISTANCE_SEARCH_MODE = 1;
    private static final int CARBEAUTY_SEARCH_MODE = 2;
    private static final int SORTTYPE_SEARCH_MODE = 3;

    @Bind(R.id.ctb_car_maintian_top_bar)
    CommonTopBar ctbCarMaintianTopBar;
    @Bind(R.id.tv_distance_mode_name)
    TextView tvDistanceModeName;
    @Bind(R.id.ll_distance_choose)
    LinearLayout llDistanceChoose;
    @Bind(R.id.tv_car_beauty_mode_name)
    TextView tvCarBeautyModeName;
    @Bind(R.id.ll_car_beauty_choose)
    LinearLayout llCarBeautyChoose;
    @Bind(R.id.tv_sort_mode_name)
    TextView tvSortModeName;
    @Bind(R.id.ll_sort_type_choose)
    LinearLayout llSortTypeChoose;
    @Bind(R.id.lv_merchant_list)
    ListView lvMerchantList;
    @Bind(R.id.tv_empty_text)
    TextView tvEmptyText;
    @Bind(R.id.ll_empty_layout)
    LinearLayout llEmptyLayout;
    private RadioGroup mRadioGroup;


    private String[] distanceModes;
    private String[] carBeautyModes;
    private String[] sortModes;
    private ListView popView;
    private PopAdapter mModeAdapter;
    private int popWindowWidth;
    private PopupWindow pop;
    private int currentMode;
    private CarMaintainMerchantListAdapter mAdapter;
    private CarMaintainMerchantPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_maintain);
        ButterKnife.bind(this);

        Resources r = getResources();
        distanceModes = r.getStringArray(R.array.distance_mode);
        carBeautyModes = r.getStringArray(R.array.car_beauty);
        sortModes = r.getStringArray(R.array.intelligence_sort_type);

        initComponent();
        initEvent();

        mPresenter = new CarMaintainMerchantPresenter(this);
        mPresenter.getCarMaintainMerchantList();
    }

    @OnClick(value = {R.id.ll_distance_choose, R.id.ll_car_beauty_choose, R.id.ll_sort_type_choose})
    public void onModeButtonClick(View view) {
        switch (view.getId()) {
            case R.id.ll_distance_choose:
                currentMode = DISTANCE_SEARCH_MODE;
                mModeAdapter.setData(distanceModes);
                break;
            case R.id.ll_car_beauty_choose:
                currentMode = CARBEAUTY_SEARCH_MODE;
                mModeAdapter.setData(carBeautyModes);
                break;
            case R.id.ll_sort_type_choose:
                currentMode = SORTTYPE_SEARCH_MODE;
                mModeAdapter.setData(sortModes);
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

    private void initEvent() {
        ctbCarMaintianTopBar.setOnTitleButtonClickListener(new CommonTopBar.OnTitleButtonClickListener() {
            @Override
            public void onTitleButtonCallback(View view, int buttonPos) {
                switch (buttonPos) {
                    case LEFT_CLICK:
                        finish();
                        break;
                    case FIRST_RIGHT_CLICK:
                        //TODO 跳到地图界面
                        break;
                }
            }
        });
        mRadioGroup.setOnCheckedChangeListener(this);
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
                switch (currentMode) {
                    case DISTANCE_SEARCH_MODE:
                        tvDistanceModeName.setText(mode);
                        break;
                    case CARBEAUTY_SEARCH_MODE:
                        tvCarBeautyModeName.setText(mode);
                        break;
                    case SORTTYPE_SEARCH_MODE:
                        tvSortModeName.setText(mode);
                        break;
                }
                if (pop != null && pop.isShowing()) {
                    pop.dismiss();
                }
                //执行更新商户列表的操作
            }
        });
    }

    private void initComponent() {
        mRadioGroup = (RadioGroup) ctbCarMaintianTopBar.getMiddleCustomLayout(R.id.rg_check_group);

        popView = new ListView(this);
        popView.setBackgroundColor(Color.WHITE);
        popView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));
        mModeAdapter = new PopAdapter(this);
        popView.setAdapter(mModeAdapter);

        tvDistanceModeName.setText(distanceModes[0]);
        tvCarBeautyModeName.setText(carBeautyModes[0]);
        tvSortModeName.setText(sortModes[0]);

        mAdapter = new CarMaintainMerchantListAdapter(this);
        lvMerchantList.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        mPresenter.onDestory();
        mAdapter.onDestory();
        super.onDestroy();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_4s_merchants:
                break;
            case R.id.rb_all_merchants:
                break;
        }
    }
}
