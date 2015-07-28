package com.fxft.cheyoufuwu.ui.homePage.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.view.CommonTopBar;
import com.fxft.cheyoufuwu.ui.homePage.adapter.MessageAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MessageActivity extends ActionBarActivity {

    @Bind(R.id.ctb_message_bar)
    CommonTopBar ctbMessageBar;
    @Bind(R.id.lv_message_list)
    ListView lvMessageList;
    @Bind(R.id.tv_empty_text)
    TextView tvEmptyText;
    @Bind(R.id.ll_empty_layout)
    LinearLayout llEmptyLayout;
    private MessageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);

        initComponent();
        initEvent();
    }

    private void initEvent() {
        ctbMessageBar.setOnTitleButtonClickListener(new CommonTopBar.OnTitleButtonClickListener() {
            @Override
            public void onTitleButtonCallback(View view, int buttonPos) {
                switch (buttonPos) {
                    case LEFT_CLICK:
                        finish();
                        break;
                    case FIRST_RIGHT_CLICK:
                        //TODO 清空界面的消息
                        break;
                }
            }
        });
    }

    private void initComponent() {
        if (mAdapter == null) {
            mAdapter = new MessageAdapter();
        }
        lvMessageList.setAdapter(mAdapter);
        if (mAdapter.getCount() == 0) {
            lvMessageList.setVisibility(View.INVISIBLE);
            llEmptyLayout.setVisibility(View.VISIBLE);
        } else {
            llEmptyLayout.setVisibility(View.GONE);
            lvMessageList.setVisibility(View.VISIBLE);
        }
        tvEmptyText.setText(getResources().getString(R.string.empty));
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
