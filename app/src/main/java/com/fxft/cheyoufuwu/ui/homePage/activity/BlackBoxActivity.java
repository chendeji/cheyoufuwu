package com.fxft.cheyoufuwu.ui.homePage.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.view.CommonTopBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BlackBoxActivity extends AppCompatActivity {

    @Bind(R.id.ctb_black_box_top_bar)
    CommonTopBar ctbBlackBoxTopBar;
    @Bind(R.id.ll_car_check)
    LinearLayout llCarCheck;
    @Bind(R.id.ll_health_record)
    LinearLayout llHealthRecord;
    @Bind(R.id.ll_drive_record)
    LinearLayout llDriveRecord;
    @Bind(R.id.ll_last_time_score)
    LinearLayout llLastTimeScore;
    @Bind(R.id.ll_car_guard)
    LinearLayout llCarGuard;
    @Bind(R.id.ll_master_help)
    LinearLayout llMasterHelp;
    @Bind(R.id.ll_maintain_notice)
    LinearLayout llMaintainNotice;
    @Bind(R.id.ll_safe_friend)
    LinearLayout llSafeFriend;
    @Bind(R.id.ll_free_check)
    LinearLayout llFreeCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_box);
        ButterKnife.bind(this);

        initEvent();
    }

    private void initEvent() {
        ctbBlackBoxTopBar.setOnTitleButtonClickListener(new CommonTopBar.OnTitleButtonClickListener() {
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
