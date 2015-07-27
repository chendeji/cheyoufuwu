package com.fxft.cheyoufuwu.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.widget.RadioGroup;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.util.AnimationUtil;
import com.fxft.cheyoufuwu.common.util.BusProvider;
import com.fxft.cheyoufuwu.common.util.ToastUtil;
import com.fxft.cheyoufuwu.common.view.CursorView;
import com.fxft.cheyoufuwu.ui.descovery.fragment.DescoveryFragment;
import com.fxft.cheyoufuwu.ui.homePage.fragment.HomePageFragment;
import com.fxft.cheyoufuwu.ui.mall.fragment.MallFragment;
import com.fxft.cheyoufuwu.ui.userCenter.fragment.UserCenterFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tab_bar)
    RadioGroup mTabBar;
    @Bind(R.id.cv_indecator)
    CursorView mIndecator;

    private int indecatorWidth;
    private HomePageFragment homePageFragment;
    private MallFragment mallFragment;
    private DescoveryFragment descoveryFragment;
    private UserCenterFragment userCenterFragment;

    private Fragment lastShowFragment;  //上一次显示的fragment
    private Fragment currentShowFragment; //当前需要显示的fragment
    private boolean isBackClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        BusProvider.getInstance().register(this);

        initFragment();
        initEvent();
    }

    /**
     * 用于初始化全部的Fragment并添加到管理者中，懒加载可能会导致用户体验卡顿
     */
    private void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        homePageFragment = HomePageFragment.newInstance();
        mallFragment = MallFragment.newInstance();
        descoveryFragment = DescoveryFragment.newInstance();
        userCenterFragment = UserCenterFragment.newInstance();
        transaction.add(R.id.fragment_holder, homePageFragment)
                .add(R.id.fragment_holder, mallFragment).hide(mallFragment)
                .add(R.id.fragment_holder, descoveryFragment).hide(descoveryFragment)
                .add(R.id.fragment_holder, userCenterFragment).hide(userCenterFragment)
                .show(homePageFragment)
                .commit();
        lastShowFragment = homePageFragment;
    }

    private void initEvent() {
        mIndecator.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                indecatorWidth = mIndecator.getMeasuredWidth();
            }
        });
        mTabBar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            int lastTransX = 0;
            int currentTransX = 0;

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //1，执行动画
                //2，切换fragment
                switch (checkedId) {
                    case R.id.rb_homepage:
                        currentTransX = 0 * indecatorWidth;
                        currentShowFragment = homePageFragment;
                        break;
                    case R.id.rb_mall:
                        currentTransX = 1 * indecatorWidth;
                        currentShowFragment = mallFragment;
                        break;
                    case R.id.rb_descovery:
                        currentTransX = 2 * indecatorWidth;
                        currentShowFragment = descoveryFragment;
                        break;
                    case R.id.rb_mine:
                        currentTransX = 3 * indecatorWidth;
                        currentShowFragment = userCenterFragment;
                        break;
                }
                Animation tranAnima = AnimationUtil.getTransAnima(lastTransX, currentTransX, 0, 0, 200, null);
                mIndecator.startAnimation(tranAnima);
                lastTransX = currentTransX;
                if (currentShowFragment == lastShowFragment) {
                    return;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (currentShowFragment != null) {
                    transaction.show(currentShowFragment);
                }
                if (lastShowFragment != null) {
                    transaction.hide(lastShowFragment);
                }
                transaction.commit();
                lastShowFragment = currentShowFragment;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            goback();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void goback() {
        if (isBackClicked){
            android.os.Process.killProcess(android.os.Process.myPid());
        } else {
            isBackClicked = true;
            ToastUtil.showShortToast(this, getResources().getString(R.string.exit));
        }
    }

    @Override
    protected void onDestroy() {
        Log.i(this.getClass().getSimpleName(), "onDestroy");
        ButterKnife.unbind(this);
        BusProvider.getInstance().unregister(this);
        super.onDestroy();
    }
}
