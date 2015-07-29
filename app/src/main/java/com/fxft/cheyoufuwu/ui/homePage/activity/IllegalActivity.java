package com.fxft.cheyoufuwu.ui.homePage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.view.CommonTopBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IllegalActivity extends AppCompatActivity {

    @Bind(R.id.ctb_illegal_query_top_bar)
    CommonTopBar ctbIllegalQueryTopBar;
    @Bind(R.id.ll_add_car)
    LinearLayout llAddCar;
    @Bind(R.id.tv_empty_text)
    TextView tvEmptyText;
    @Bind(R.id.ll_empty_layout)
    LinearLayout llEmptyLayout;
    @Bind(R.id.lv_illegal_list)
    ListView lvIllegalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illegal);
        ButterKnife.bind(this);

        initEvent();
    }

    private void initEvent() {
        ctbIllegalQueryTopBar.setOnTitleButtonClickListener(new CommonTopBar.OnTitleButtonClickListener() {
            @Override
            public void onTitleButtonCallback(View view, int buttonPos) {
                finish();
            }
        });
    }

    @OnClick(value = {R.id.ll_add_car})
    public void onAddCarButtonClick(View v){
        Intent intent = new Intent(this, AddCarActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
