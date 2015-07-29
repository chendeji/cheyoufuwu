package com.fxft.cheyoufuwu.ui.homePage.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.view.CommonTopBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class IntegralExchangeActivity extends ActionBarActivity {

    @Bind(R.id.ctb_integral_exchange_top_bar)
    CommonTopBar ctbIntegralExchangeTopBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral_exchange);
        ButterKnife.bind(this);

        initEvent();
    }

    private void initEvent() {
        ctbIntegralExchangeTopBar.setOnTitleButtonClickListener(new CommonTopBar.OnTitleButtonClickListener() {
            @Override
            public void onTitleButtonCallback(View view, int buttonPos) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
