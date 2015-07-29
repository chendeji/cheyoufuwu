package com.fxft.cheyoufuwu.ui.homePage.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.util.HeadImageUtil;
import com.fxft.cheyoufuwu.common.view.CommonTopBar;
import com.fxft.cheyoufuwu.ui.common.view.ChoosePictureDialog;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddCarActivity extends AppCompatActivity implements HeadImageUtil.OnImageSuccessListener {

    @Bind(R.id.ctb_binding_info_top_bar)
    CommonTopBar ctbBindingInfoTopBar;
    @Bind(R.id.tv_select_car_brand)
    TextView tvSelectCarBrand;
    @Bind(R.id.et_license_plate_number)
    EditText etLicensePlateNumber;
    @Bind(R.id.et_frame_number)
    EditText etFrameNumber;
    @Bind(R.id.et_engine_number)
    EditText etEngineNumber;
    @Bind(R.id.et_mileage_number)
    EditText etMileageNumber;
    @Bind(R.id._choose_next_maintenance_time)
    TextView ChooseNextMaintenanceTime;
    @Bind(R.id.bt_save)
    Button btSave;
    @Bind(R.id.iv_car_image)
    ImageView ivCarImage;
    private ChoosePictureDialog mChoosePicturedialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
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

        ivCarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoosePictureDialog();
            }
        });
        HeadImageUtil.setOnImageSuccessListener(this);
    }

    /**
     * 显示选取图片dialog
     */
    private void showChoosePictureDialog() {
        if (mChoosePicturedialog == null) {
            mChoosePicturedialog = new ChoosePictureDialog(this, R.style.common_dialog_style);
        }
        mChoosePicturedialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 用户没有进行有效的设置操作，返回
        HeadImageUtil.onActivityResult(this, requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        HeadImageUtil.setOnImageSuccessListener(null);
        super.onDestroy();
    }

    @Override
    public void onGetPhoto(final Bitmap photo) {
        ivCarImage.post(new Runnable() {
            @Override
            public void run() {
                ivCarImage.setImageBitmap(photo);
            }
        });
    }
}
