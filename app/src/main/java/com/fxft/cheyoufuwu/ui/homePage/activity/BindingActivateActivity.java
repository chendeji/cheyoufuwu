package com.fxft.cheyoufuwu.ui.homePage.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.view.CommonTopBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindingActivateActivity extends ActionBarActivity {

    @Bind(R.id.ctb_binding_info_top_bar)
    CommonTopBar ctbBindingInfoTopBar;
    @Bind(R.id.rl_cheyou_activate)
    RelativeLayout rlCheyouActivate;
    @Bind(R.id.rl_black_box_binding)
    RelativeLayout rlBlackBoxBinding;
    @Bind(R.id.rl_rescul_car_binding)
    RelativeLayout rlResculCarBinding;
    @Bind(R.id.rl_wash_card_binding)
    RelativeLayout rlWashCardBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding);
        ButterKnife.bind(this);

        initEvent();
    }

    private void initEvent() {
        ctbBindingInfoTopBar.setOnTitleButtonClickListener(new CommonTopBar.OnTitleButtonClickListener() {
            @Override
            public void onTitleButtonCallback(View view, int buttonPos) {
                finish();
            }
        });
    }

    @OnClick(value = {R.id.rl_cheyou_activate, R.id.rl_black_box_binding, R.id.rl_rescul_car_binding, R.id.rl_wash_card_binding})
    public void onBindFuncButtonClick(View v) {
        switch (v.getId()) {
            case R.id.rl_cheyou_activate:
                break;
            case R.id.rl_black_box_binding:
                break;
            case R.id.rl_rescul_car_binding:
                break;
            case R.id.rl_wash_card_binding:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
