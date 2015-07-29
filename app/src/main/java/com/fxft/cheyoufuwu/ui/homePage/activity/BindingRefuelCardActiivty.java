package com.fxft.cheyoufuwu.ui.homePage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.view.CommonTopBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindingRefuelCardActiivty extends ActionBarActivity {

    @Bind(R.id.tv_personal_binding)
    TextView tvPersonalBinding;
    @Bind(R.id.tv_company_binding)
    TextView tvCompanyBinding;
    @Bind(R.id.ctb_binding_car_top_bar)
    CommonTopBar ctbBindingCarTopBar;

    private int mCurrentBindingMode = BindingInfoInputActivity.PERSONAL_BINDING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_refuel_card);
        ButterKnife.bind(this);

        initEvent();
    }

    private void initEvent() {
        ctbBindingCarTopBar.setOnTitleButtonClickListener(new CommonTopBar.OnTitleButtonClickListener() {
            @Override
            public void onTitleButtonCallback(View view, int buttonPos) {
                finish();
            }
        });
    }

    @OnClick({R.id.tv_personal_binding, R.id.tv_company_binding})
    public void onBindingClick(View view) {
        switch (view.getId()){
            case R.id.tv_personal_binding:
                mCurrentBindingMode = BindingInfoInputActivity.PERSONAL_BINDING;
                break;
            case R.id.tv_company_binding:
                mCurrentBindingMode = BindingInfoInputActivity.COMPANY_BINDING;
                break;
        }
        Intent intent = new Intent(this, BindingInfoInputActivity.class);
        intent.putExtra(BindingInfoInputActivity.BINDING_MODE, mCurrentBindingMode);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
