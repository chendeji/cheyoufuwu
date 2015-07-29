package com.fxft.cheyoufuwu.ui.homePage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.view.CommonTopBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RescueActivity extends AppCompatActivity {

    @Bind(R.id.ctb_rescue_service_top_bar)
    CommonTopBar ctbRescueServiceTopBar;
    @Bind(R.id.ll_add_car)
    LinearLayout llAddCar;
    @Bind(R.id.sos_tuoche)
    LinearLayout sosTuoche;
    @Bind(R.id.sos_meiyou)
    LinearLayout sosMeiyou;
    @Bind(R.id.sos_baotai)
    LinearLayout sosBaotai;
    @Bind(R.id.sos_beikun)
    LinearLayout sosBeikun;
    @Bind(R.id.sos_quedian)
    LinearLayout sosQuedian;
    @Bind(R.id.sos_qita)
    LinearLayout sosQita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescue);
        ButterKnife.bind(this);
        initEvent();
    }

    private void initEvent() {
        ctbRescueServiceTopBar.setOnTitleButtonClickListener(new CommonTopBar.OnTitleButtonClickListener() {
            @Override
            public void onTitleButtonCallback(View view, int buttonPos) {
                finish();
            }
        });
    }

    @OnClick(value = {R.id.ll_add_car})
    public void onAddCarButtonClick(View view){
        //TODO 跳入到添加车辆界面
        Intent intent = new Intent(this, AddCarActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
