package com.fxft.cheyoufuwu.common.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.fxft.cheyoufuwu.R;


/**
 * Created by chendeji on 19/6/15.
 */
public class CommonSearchLayout extends RelativeLayout {

    private String hint;
    private Drawable left_image;
    private Drawable right_cancel_image;
    private ImageView search_image;
    private EditText ed_text;
    private ImageView cancel_image;

    private OnSearchKeyWordChanged keyWordChanged;
    private float text_size;
    private int mHintColor;
    private int mTextColor;

    public interface OnSearchKeyWordChanged {
        /**
         * 搜索关键字变化
         * @param keyWord 关键字
         */
        void onKeyChanged(String keyWord);

        /**
         * 清空输入框
         */
        void onKeyClear();
    }

    public void setKeyWordChangedListener(OnSearchKeyWordChanged listener) {
        this.keyWordChanged = listener;
    }

    public CommonSearchLayout(Context context) {
        super(context);
        init();
    }

    public CommonSearchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypeValue(context, attrs, 0);
        init();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public CommonSearchLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeValue(context, attrs, defStyleAttr);
        init();
    }

    private void initTypeValue(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonSearchLayout, defStyleAttr, 0);
        hint = typedArray.getString(R.styleable.CommonSearchLayout_edit_text_hint);
        left_image = typedArray.getDrawable(R.styleable.CommonSearchLayout_left_image);
        right_cancel_image = typedArray.getDrawable(R.styleable.CommonSearchLayout_cancel_image);
        text_size = typedArray.getDimension(R.styleable.CommonSearchLayout_edit_text_size, getResources().getDimension(R.dimen.default_text_size));
        mHintColor = typedArray.getColor(R.styleable.CommonSearchLayout_edit_text_hint_color, Color.BLACK);
        mTextColor = typedArray.getColor(R.styleable.CommonSearchLayout_edit_text_color, Color.BLACK);
        typedArray.recycle();
    }

    private void init() {
        View viewContainer = LayoutInflater.from(getContext()).inflate(R.layout.common_search_layout, this, true);
        search_image = (ImageView) viewContainer.findViewById(R.id.iv_left_search_image);
        ed_text = (EditText) viewContainer.findViewById(R.id.et_city_input);
        ed_text.setTextColor(mTextColor);
        ed_text.setTextSize(text_size);
        cancel_image = (ImageView) viewContainer.findViewById(R.id.iv_right_cancel_image);

        if (left_image != null) {
            search_image.setImageDrawable(left_image);
        } else {
            search_image.setImageDrawable(getContext().getResources().getDrawable(android.R.drawable.ic_menu_search));
        }

        if (right_cancel_image != null) {
            cancel_image.setImageDrawable(right_cancel_image);
        } else {
            cancel_image.setImageDrawable(getContext().getResources().getDrawable(android.R.drawable.ic_menu_close_clear_cancel));
        }

        initData();
        initEvent();
    }

    private void initData() {
        //主页面初始化时调用
        ed_text.setHint(hint);
        ed_text.setHintTextColor(mHintColor);
        hideCancelButton();
        if (keyWordChanged != null) {
            keyWordChanged.onKeyClear();
        }
    }

    private void hideCancelButton() {
        cancel_image.setVisibility(View.INVISIBLE);
    }

    private void showCancelButton() {
        cancel_image.setVisibility(View.VISIBLE);
    }

    private void initEvent() {
        cancel_image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_text.setText("");
                if (keyWordChanged != null) {
                    keyWordChanged.onKeyClear();
                }
            }
        });
        ed_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String search_key = s.toString();
                int length = search_key.length();
                if (length > 0) {
                    showCancelButton();
                    //回调函数
                    if (keyWordChanged != null) {
                        keyWordChanged.onKeyChanged(search_key);
                    }
                } else if (length == 0) {
                    hideCancelButton();
                    if (keyWordChanged != null) {
                        keyWordChanged.onKeyClear();
                    }
                }
            }
        });
    }
}
