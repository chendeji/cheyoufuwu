package com.fxft.cheyoufuwu.ui.homePage.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.view.CommonSearchLayout;
import com.fxft.cheyoufuwu.common.view.CommonTopBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchMerchantActivity extends AppCompatActivity {

    private static final String TAG = SearchMerchantActivity.class.getSimpleName();

    @Bind(R.id.ctb_search_merchant_bar)
    CommonTopBar mSearchMerchantBar;
    @Bind(R.id.rg_check_group)
    RadioGroup rgCheckGroup;
    @Bind(R.id.bt_clear_search_record)
    Button btClearSearchRecord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_merchant);
        ButterKnife.bind(this);

        initEvent();
        initFragment();
    }

    private void initFragment() {
        //TODO 初始化搜索记录，商家列表以及商户列表的fragment。
    }

    private void initEvent() {
        btClearSearchRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 清空所有的搜索记录
            }
        });
        mSearchMerchantBar.setOnTitleButtonClickListener(new CommonTopBar.OnTitleButtonClickListener() {
            @Override
            public void onTitleButtonCallback(View view, int buttonPos) {
                switch (buttonPos) {
                    case FIRST_RIGHT_CLICK:
                        finish();
                        break;
                }
            }
        });
        ((CommonSearchLayout)mSearchMerchantBar.getMiddleCustomLayout(R.id.csl_common_search_layout)).setKeyWordChangedListener(new CommonSearchLayout.OnSearchKeyWordChanged() {
            @Override
            public void onKeyChanged(String keyWord) {
                Log.i(TAG, "onKeyChanged:" + keyWord);
                //TODO 切换到当前check的fragment
            }

            @Override
            public void onKeyClear() {
                //TODO 切换到搜索记录界面

            }
        });
        rgCheckGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_merchant:
                        break;
                    case R.id.rb_merchandise:
                        break;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
