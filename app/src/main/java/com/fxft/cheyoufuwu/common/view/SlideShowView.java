package com.fxft.cheyoufuwu.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.fxft.cheyoufuwu.R;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 用于顶部显示滚动广告的控件
 * 1，在后台加载完毕获取图片之后显示
 * 2，自动滚动。
 *
 * @author chendj
 */
public class SlideShowView extends FrameLayout {

    private static final String TAG = SlideShowView.class.getSimpleName();
    /**
     * 默认的滚动时间间隔
     */
    private static final int DEFUAL_DELAY = 2;
    private static final int DEFUAL_PERIOD = 4;

    ViewPager ivTopImage;

    private int mScrollState;
    private PagerAdapter mAdapter;
    private ScheduledExecutorService scheduledExecutorService;
    private OnSlideViewScrollListener onScrollListener;
    private boolean isAutoScrollEnable;

    public interface OnSlideViewScrollListener {
        /**
         * 向右滑动
         */
        int RIGHT_SCROLL = 0x0;

        /**
         * 向左滑动
         */
        int LEFT_SCROLL = 0X1;

        /**
         * 滑动检测
         *
         * @param scrollDerection      滑动的方向
         * @param position             位置
         * @param positionOffset       滑动的比例
         * @param positionOffsetPixels 滑动的值（相对于屏幕宽度）
         */
        void onPageScrolled(int scrollDerection, int position, float positionOffset, int positionOffsetPixels);

        /**
         * 当前被选中的页面
         *
         * @param position 页面位置
         */
        void onPageSelected(int position);
    }

    public void setOnSlideViewScrollListener(OnSlideViewScrollListener listener) {
        this.onScrollListener = listener;
    }

    public SlideShowView(Context context) {
        this(context, null);
        init(context, null);
    }

    public SlideShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SlideShowView);
        isAutoScrollEnable = a.getBoolean(R.styleable.SlideShowView_auto_scroll_enable, false);
        a.recycle();
        LayoutInflater.from(getContext()).inflate(R.layout.top_image_layout, this, true);
        ivTopImage = (ViewPager) findViewById(R.id.iv_top_image);
        if (mAdapter != null) {
            ivTopImage.setAdapter(mAdapter);
        }
        initEvent();
        startLoop();
    }

    public void setAdapter(PagerAdapter adapter) {
        this.mAdapter = adapter;
        ivTopImage.setAdapter(mAdapter);
    }

    /**
     * 设置空间是否自动轮播
     *
     * @param enable the enable
     */
    public void setAutoScrollEnable(boolean enable) {
        this.isAutoScrollEnable = enable;
    }

    private void initEvent() {
        ivTopImage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public int prePosition;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffsetPixels != 0) {
                    int derection = -1;
                    if (prePosition > position) {
                        //向右滑动 position - 1
                        derection = OnSlideViewScrollListener.RIGHT_SCROLL;
                    } else {
                        //向左滑动 position + 1
                        derection = OnSlideViewScrollListener.LEFT_SCROLL;
                    }
                    if (onScrollListener != null) {
                        onScrollListener.onPageScrolled(derection, position, positionOffset, positionOffsetPixels);
                    }
                    if (positionOffset == 0) {
                        prePosition = position;
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (onScrollListener != null) {
                    onScrollListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                mScrollState = state;
            }
        });
    }

    private void startLoop() {
        if (scheduledExecutorService == null) {
            scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        }
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (mScrollState == ViewPager.SCROLL_STATE_IDLE
                        && mAdapter != null
                        && isAutoScrollEnable) {
                    //手指正在拖动ViewPager的时候不执行自动滚动
                    //如果adapter没有数据，也不执行
                    ivTopImage.post(new Runnable() {
                        @Override
                        public void run() {
                            int currentItemPosition = ivTopImage.getCurrentItem();
                            ivTopImage.setCurrentItem(++currentItemPosition, true);
                        }
                    });
                }

            }
        }, DEFUAL_DELAY, DEFUAL_PERIOD, TimeUnit.SECONDS);
    }
}
