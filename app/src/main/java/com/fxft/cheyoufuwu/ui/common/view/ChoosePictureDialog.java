package com.fxft.cheyoufuwu.ui.common.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.util.HeadImageUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ChenDJ on 2015/7/29.<br>
 */
public class ChoosePictureDialog extends Dialog implements DialogInterface.OnDismissListener {

    private final Context mContext;
    @Bind(R.id.bt_choose_picture_from_gallary)
    Button btChoosePictureFromGallary;
    @Bind(R.id.bt_choose_picture_camera)
    Button btChoosePictureCamera;
    @Bind(R.id.bt_cancle)
    Button btCancle;

    public ChoosePictureDialog(Context context, int theme) {
        super(context, theme);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.usercenter_choose_picture_dialog_layout);
        ButterKnife.bind(this);
        // 设置对话框横向充满，垂直自适应并居中
        Window mWindow = this.getWindow();
        mWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mWindow.setGravity(Gravity.BOTTOM | Gravity.CENTER_VERTICAL);

        initEvent();
        super.onCreate(savedInstanceState);
    }

    private void initEvent() {
        setOnDismissListener(this);
        btChoosePictureFromGallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //从相册选取图片
                HeadImageUtil.choseHeadImageFromGallery((Activity)mContext);
                dismiss();
            }
        });
        btChoosePictureCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相机拍照
                HeadImageUtil.choseHeadImageFromCameraCapture((Activity)mContext);
                dismiss();
            }
        });
        btCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        ButterKnife.unbind(this);
    }
}
