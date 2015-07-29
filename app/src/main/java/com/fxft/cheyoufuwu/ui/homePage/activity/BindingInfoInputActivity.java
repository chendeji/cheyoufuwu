package com.fxft.cheyoufuwu.ui.homePage.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.view.CommonTopBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BindingInfoInputActivity extends AppCompatActivity {

    public static final String BINDING_MODE = "binding_mode";

    public static final int PERSONAL_BINDING = 0x0; //个人绑定
    public static final int COMPANY_BINDING = 0x1;  //企业绑定
    @Bind(R.id.ctb_binding_info_top_bar)
    CommonTopBar ctbBindingInfoTopBar;
    @Bind(R.id.et_input_refuel_card_number)
    EditText etInputRefuelCardNumber;
    @Bind(R.id.et_input_ID_name)
    EditText etInputIDName;
    @Bind(R.id.et_ID_number)
    EditText etIDNumber;
    @Bind(R.id.bt_binding)
    Button btBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_info);
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
}
