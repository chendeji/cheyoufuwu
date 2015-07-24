package com.fxft.cheyoufuwu.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fxft.cheyoufuwu.R;


/**
 * 支持所有位置的自定义布局，默认可以只显示图片，支持点击事件
 */
public class CommonTopBar extends RelativeLayout implements View.OnClickListener {

    /**
     * 顶部title
     */
    private String mTitle;

    /**
     * 顶部title 颜色
     */
    private int mTitleColor;

    /**
     * 左边按钮文字的颜色
     */
    private int mLeftTextColor;

    /**
     * 左边按钮文字的大小
     */
    private float mLeftTextSize;

    /**
     * 顶部title 文字的大小
     */
    private float mTitleSize;

    /**
     * 右边按钮文字的颜色
     */
    private int mRightTextColor;

    /**
     * 右边按钮文字的大小
     */
    private float mRightTextSize;

    /**
     * 左边的按钮的背景图片
     */
    private int mLeftBackground;

    /**
     * 右边按钮背景图片1
     */
    private int mRightBackgroundFirst;

    /**
     * 右边按钮背景图片2
     */
    private int mRightBackgroundSec;

    /**
     * 右边按钮背景图片3
     */
    private int mRightBackgroundThir;

    /**
     * 中间自定义布局的layout_id
     */
    private int mMiddleCustomLayoutResourceId;

    /**
     * 左边自定义布局id
     */
    private int mLeftCustomLayoutResouceID;

    /**
     * 右边三个自定义布局的资源ID
     */
    private int mRightFriCustomLayoutResouceID;
    private int mRightSecCustomLayoutResouceID;
    private int mRightThirCustomLayoutResouceID;

    /**
     * 是否显示中间的title
     */
    private boolean mTitleEnable;

    /**
     * 是否显示中间的自定义布局
     */
    private boolean mMiddleCustomLayoutEnable;

    /**
     * 是否显示左边的自定义布局
     */
    private boolean mLeftCustomLayoutEnable;

    /**
     * 是否显示右边的三个自定义布局
     */
    private boolean mRightFriCustomLayoutEnable,
            mRightSecCustomLayoutEnable,
            mRightThirCustomLayoutEnable;

    /**
     * 左边的布局自定义view
     */
    private View mLeftCustomLayout;

    /**
     * 中间的布局自定义view
     */
    private View mMiddleCustomLayout;

    /**
     * 右边的布局自定义view1
     */
    private View mRightCustomLayout1;
    /**
     * 右边的布局自定义view2
     */
    private View mRightCustomLayout2;
    /**
     * 右边的布局自定义view3
     */
    private View mRightCustomLayout3;

    /**
     * 左 中 右 三个部分的自定义布局Holder
     */
    private RelativeLayout mLeftButtonLayoutHolder,
            mRightFirButtonLayoutHolder, mRightSecButtonLayoutHolder, mRightThirButtonLayoutHolder;
    private LinearLayout mMiddleCustomLayoutHolder;

    /**
     * 中间的title文本的holder
     */
    private RelativeLayout mTitleLayoutHolder;
    private TextView mTitleView;

    /**
     * 菜单栏按钮点击事件
     */
    private OnTitleButtonClickListener mTitleButtonClickListener;

    /**
     * {@link OnTitleButtonClickListener#LEFT_CLICK}
     * {@link OnTitleButtonClickListener#MIDDLE_CLICK}
     * {@link OnTitleButtonClickListener#FIRST_RIGHT_CLICK}
     * {@link OnTitleButtonClickListener#SEC_RIGHT_CLICK}
     * {@link OnTitleButtonClickListener#THIR_RIGHT_CLICK}
     */
    public interface OnTitleButtonClickListener {
        /**
         * 左边按钮点击
         */
        int LEFT_CLICK = 0x0;
        /**
         * 中间的自定义控件被点击
         */
        int MIDDLE_CLICK = 0x1; //中间的自定义控件被点击
        /**
         * 右边第一个按钮被点击
         */
        int FIRST_RIGHT_CLICK = 0x2;    //右边第一个按钮被点击
        /**
         * 右边第二个按钮被点击
         */
        int SEC_RIGHT_CLICK = 0x3;  //右边第二个按钮被点击
        /**
         * 右边第三个按钮被点击
         */
        int THIR_RIGHT_CLICK = 0x4; //右边第三个按钮被点击

        void onTitleButtonCallback(View view, int buttonPos);
    }

    public void setOnTitleButtonClickListener(OnTitleButtonClickListener listener) {
        mTitleButtonClickListener = listener;
    }

    public CommonTopBar(Context context) {
        this(context, null);
    }

    public CommonTopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs, defStyleAttr);
        initComponent();
    }

    private void initComponent() {
        View viewContainer = LayoutInflater.from(getContext()).inflate(R.layout.common_top_bar, this, true);

        //左边和右边的按钮
        mLeftButtonLayoutHolder = (RelativeLayout) viewContainer.findViewById(R.id.rl_left_image_holder);
        mRightFirButtonLayoutHolder = (RelativeLayout) viewContainer.findViewById(R.id.rl_right_image1_holder);
        mRightSecButtonLayoutHolder = (RelativeLayout) viewContainer.findViewById(R.id.rl_right_image2_holder);
        mRightThirButtonLayoutHolder = (RelativeLayout) viewContainer.findViewById(R.id.rl_right_image3_holder);

        //中间的title 和 自定义布局
        mMiddleCustomLayoutHolder = (LinearLayout) viewContainer.findViewById(R.id.ll_middle_layout_holder);
        mTitleLayoutHolder = (RelativeLayout) viewContainer.findViewById(R.id.rl_title_layout);
        mTitleView = (TextView) viewContainer.findViewById(R.id.tv_title);
        mTitleView.setTextColor(mTitleColor);
        mTitleView.setTextSize(mTitleSize);

        //自定义布局的加载
        if (mMiddleCustomLayoutResourceId > 0) {
            LayoutInflater.from(getContext()).inflate(mMiddleCustomLayoutResourceId, mMiddleCustomLayoutHolder, true);
        }
        if (mLeftCustomLayoutResouceID > 0) {
            LayoutInflater.from(getContext()).inflate(mLeftCustomLayoutResouceID, mLeftButtonLayoutHolder, true);
        }
        if (mRightFriCustomLayoutResouceID > 0) {
            LayoutInflater.from(getContext()).inflate(mRightFriCustomLayoutResouceID, mRightFirButtonLayoutHolder, true);
        }
        if (mRightSecCustomLayoutResouceID > 0) {
            LayoutInflater.from(getContext()).inflate(mRightSecCustomLayoutResouceID, mRightSecButtonLayoutHolder, true);
        }
        if (mRightThirCustomLayoutResouceID > 0) {
            LayoutInflater.from(getContext()).inflate(mRightThirCustomLayoutResouceID, mRightThirButtonLayoutHolder, true);
        }

        initComponentValue();
        initViewEnable();
        initEvent();
    }

    private void initEvent() {
        mLeftButtonLayoutHolder.setOnClickListener(this);
        mRightFirButtonLayoutHolder.setOnClickListener(this);
        mRightSecButtonLayoutHolder.setOnClickListener(this);
        mRightThirButtonLayoutHolder.setOnClickListener(this);
        mMiddleCustomLayoutHolder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int buttonPos = -1;
        if (v == mLeftButtonLayoutHolder){
            buttonPos = OnTitleButtonClickListener.LEFT_CLICK;
        }
        if (v == mRightFirButtonLayoutHolder){
            buttonPos = OnTitleButtonClickListener.FIRST_RIGHT_CLICK;
        }
        if (v == mRightSecButtonLayoutHolder){
            buttonPos = OnTitleButtonClickListener.SEC_RIGHT_CLICK;
        }
        if (v == mRightThirButtonLayoutHolder){
            buttonPos = OnTitleButtonClickListener.THIR_RIGHT_CLICK;
        }
        if (v == mMiddleCustomLayoutHolder){
            buttonPos = OnTitleButtonClickListener.MIDDLE_CLICK;
        }
        if (mTitleButtonClickListener != null) {
            mTitleButtonClickListener.onTitleButtonCallback(v, buttonPos);
        }
    }

    private void initComponentValue() {
        if (mLeftBackground > 0) {
            ImageView leftImage = new ImageView(getContext());
            leftImage.setBackgroundResource(mLeftBackground);
            mLeftButtonLayoutHolder.addView(leftImage);
        }

        if (mRightBackgroundFirst > 0) {
            ImageView rightFirstImage = new ImageView(getContext());
            rightFirstImage.setBackgroundResource(mRightBackgroundFirst);
            mRightFirButtonLayoutHolder.addView(rightFirstImage);
        }

        if (mRightBackgroundSec > 0) {
            ImageView rightSecImage = new ImageView(getContext());
            rightSecImage.setBackgroundResource(mRightBackgroundSec);
            mRightSecButtonLayoutHolder.addView(rightSecImage);
        }

        if (mRightBackgroundThir > 0) {
            ImageView rightThirImage = new ImageView(getContext());
            rightThirImage.setBackgroundResource(mRightBackgroundThir);
            mRightThirButtonLayoutHolder.addView(rightThirImage);
        }

        if (!TextUtils.isEmpty(mTitle)) {
            mTitleView.setText(mTitle);
        }

    }

    private void initViewEnable() {
        //检查所有控件是否可用
        if (!mLeftCustomLayoutEnable) {
            mLeftButtonLayoutHolder.setVisibility(View.GONE);
        }
        if (!mRightFriCustomLayoutEnable) {
            mRightFirButtonLayoutHolder.setVisibility(View.GONE);
        }
        if (!mRightSecCustomLayoutEnable) {
            mRightSecButtonLayoutHolder.setVisibility(View.GONE);
        }
        if (!mRightThirCustomLayoutEnable) {
            mRightThirButtonLayoutHolder.setVisibility(View.GONE);
        }
        if (!mMiddleCustomLayoutEnable) {
            mMiddleCustomLayoutHolder.setVisibility(View.GONE);
        }
        if (!mTitleEnable) {
            mTitleLayoutHolder.setVisibility(View.GONE);
        }
    }

    private void initAttr(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CommonTopBar, defStyleAttr, 0);
        if (a.hasValue(R.styleable.CommonTopBar_title_enable)) {
            mTitleEnable = a.getBoolean(R.styleable.CommonTopBar_title_enable, true);
            if (mTitleEnable) {
                if (a.hasValue(R.styleable.CommonTopBar_title_text)) {
                    mTitle = a.getString(R.styleable.CommonTopBar_title_text);
                }
            }
        }

        if (a.hasValue(R.styleable.CommonTopBar_title_text_color)) {
            mTitleColor = a.getColor(R.styleable.CommonTopBar_title_text_color, Color.BLACK);
        }
        if (a.hasValue(R.styleable.CommonTopBar_title_text_size)) {
            mTitleSize = a.getDimensionPixelSize(R.styleable.CommonTopBar_title_text_size,
                    (int) getResources().getDimension(R.dimen.default_text_size));
        }

        if (a.hasValue(R.styleable.CommonTopBar_middle_custom_layout_enable)) {
            mMiddleCustomLayoutEnable = a.getBoolean(R.styleable.CommonTopBar_middle_custom_layout_enable, false);
        }
        if (a.hasValue(R.styleable.CommonTopBar_middle_custom_layout)) {
            mMiddleCustomLayoutResourceId = a.getResourceId(R.styleable.CommonTopBar_middle_custom_layout, Integer.MIN_VALUE);
        }

        if (a.hasValue(R.styleable.CommonTopBar_left_bg)) {
            mLeftBackground = a.getResourceId(R.styleable.CommonTopBar_left_bg, Integer.MIN_VALUE);
        }
        if (a.hasValue(R.styleable.CommonTopBar_left_layout_enable)) {
            mLeftCustomLayoutEnable = a.getBoolean(R.styleable.CommonTopBar_left_layout_enable, true);
        }
        if (a.hasValue(R.styleable.CommonTopBar_left_custom_layout)) {
            mLeftCustomLayoutResouceID = a.getResourceId(R.styleable.CommonTopBar_left_custom_layout, Integer.MIN_VALUE);
        }


        if (a.hasValue(R.styleable.CommonTopBar_right_bg1)) {
            mRightBackgroundFirst = a.getResourceId(R.styleable.CommonTopBar_right_bg1, Integer.MIN_VALUE);
        }
        if (a.hasValue(R.styleable.CommonTopBar_right_bg2)) {
            mRightBackgroundSec = a.getResourceId(R.styleable.CommonTopBar_right_bg2, Integer.MIN_VALUE);
        }
        if (a.hasValue(R.styleable.CommonTopBar_right_bg3)) {
            mRightBackgroundThir = a.getResourceId(R.styleable.CommonTopBar_right_bg3, Integer.MIN_VALUE);
        }

        if (a.hasValue(R.styleable.CommonTopBar_right_first_layout_enable)) {
            mRightFriCustomLayoutEnable = a.getBoolean(R.styleable.CommonTopBar_right_first_layout_enable, false);
        }
        if (a.hasValue(R.styleable.CommonTopBar_right_first_layout)) {
            mRightFriCustomLayoutResouceID = a.getResourceId(R.styleable.CommonTopBar_right_first_layout, Integer.MIN_VALUE);
        }

        if (a.hasValue(R.styleable.CommonTopBar_right_sec_layout_enable)) {
            mRightSecCustomLayoutEnable = a.getBoolean(R.styleable.CommonTopBar_right_sec_layout_enable, false);
        }
        if (a.hasValue(R.styleable.CommonTopBar_right_sec_layout)) {
            mRightSecCustomLayoutResouceID = a.getResourceId(R.styleable.CommonTopBar_right_sec_layout, Integer.MIN_VALUE);
        }

        if (a.hasValue(R.styleable.CommonTopBar_right_thir_layout_enable)) {
            mRightThirCustomLayoutEnable = a.getBoolean(R.styleable.CommonTopBar_right_thir_layout_enable, false);
        }
        if (a.hasValue(R.styleable.CommonTopBar_right_thir_layout)) {
            mRightThirCustomLayoutResouceID = a.getResourceId(R.styleable.CommonTopBar_right_thir_layout, Integer.MIN_VALUE);
        }

        a.recycle();
    }
}
