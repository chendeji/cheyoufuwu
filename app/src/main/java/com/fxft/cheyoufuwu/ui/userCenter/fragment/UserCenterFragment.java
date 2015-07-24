package com.fxft.cheyoufuwu.ui.userCenter.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fxft.cheyoufuwu.R;
import com.fxft.cheyoufuwu.common.util.SystemUtil;
import com.fxft.cheyoufuwu.common.util.ToastUtil;
import com.fxft.cheyoufuwu.common.view.CommonTopBar;
import com.fxft.cheyoufuwu.common.view.scrollview.ObservableScrollView;
import com.fxft.cheyoufuwu.ui.userCenter.iView.IUserCenterView;
import com.fxft.cheyoufuwu.ui.userCenter.presenter.UserCenterPresenter;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnUserCenterFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserCenterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserCenterFragment extends Fragment implements IUserCenterView {

    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";

    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    private static int output_X = 480;
    private static int output_Y = 480;

    @Bind(R.id.ctb_usercenter_top_bar)
    CommonTopBar mUsercenterTopBar;
    @Bind(R.id.iv_user_headimage)
    ImageView ivUserHeadimage;
    @Bind(R.id.tv_user_nickname)
    TextView tvUserNickname;
    @Bind(R.id.tv_vip_level)
    TextView tvVipLevel;
    @Bind(R.id.tv_article_collection_count)
    TextView tvArticleCollectionCount;
    @Bind(R.id.tv_volume_collection_count)
    TextView tvVolumeCollectionCount;
    @Bind(R.id.tv_merchant_collection_count)
    TextView tvMerchantCollectionCount;
    @Bind(R.id.rl_my_quan)
    RelativeLayout rlMyQuan;
    @Bind(R.id.rl_my_order)
    RelativeLayout rlMyOrder;
    @Bind(R.id.rl_my_wallet)
    RelativeLayout rlMyWallet;
    @Bind(R.id.rl_my_car)
    RelativeLayout rlMyCar;
    @Bind(R.id.rl_my_bunding)
    RelativeLayout rlMyBunding;
    @Bind(R.id.tv_customer_service_phone_number)
    TextView tvCustomerServicePhoneNumber;
    @Bind(R.id.rl_call_customer_service)
    RelativeLayout rlCallCustomerService;
    @Bind(R.id.sv_usercenter_scroll_layout)
    ObservableScrollView svUsercenterScrollLayout;
    private OnUserCenterFragmentInteractionListener mListener;
    private UserCenterPresenter userPresenter;
    private ChoosePictureDialog mChoosePicturedialog;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment UserCenterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserCenterFragment newInstance() {
        UserCenterFragment fragment = new UserCenterFragment();
        return fragment;
    }

    public UserCenterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_center, container, false);
        ButterKnife.bind(this, view);
        initEvent();
        return view;
    }

    private void initEvent() {
        ivUserHeadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击头像弹出选择框
                showChoosePictureDialog();
            }
        });
        mUsercenterTopBar.setOnTitleButtonClickListener(new CommonTopBar.OnTitleButtonClickListener() {
            @Override
            public void onTitleButtonCallback(View view, int buttonPos) {
                switch (buttonPos) {
                    case LEFT_CLICK:
                        ToastUtil.showShortToast(getActivity(), "LEFT_CLICK");
                        break;
                }
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 用户没有进行有效的设置操作，返回
        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(data.getData());
                break;

            case CODE_CAMERA_REQUEST:
                if (SystemUtil.hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
                    cropRawPhoto(Uri.fromFile(tempFile));
                }
                break;

            case CODE_RESULT_REQUEST:
                if (data != null) {
                    setImageToHeadView(data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 到本地相册选取图片
     */
    private void choseHeadImageFromGallery() {
        Intent intentFromGallery = new Intent();
        // 设置文件类型
        intentFromGallery.setType("image/*");
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
    }

    /**
     * Chose head image from camera capture.
     */
    private void choseHeadImageFromCameraCapture() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 判断存储卡是否可用，存储照片文件
        if (SystemUtil.hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                    .fromFile(new File(Environment
                            .getExternalStorageDirectory(), IMAGE_FILE_NAME)));
        }

        startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
    }


    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        // 设置裁剪
        intent.putExtra("crop", "true");

        // aspectX , aspectY :宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX , outputY : 裁剪图片宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            final Bitmap photo = extras.getParcelable("data");
            ivUserHeadimage.post(new Runnable() {
                @Override
                public void run() {
                    ivUserHeadimage.setImageBitmap(photo);
                }
            });

        }
    }

    /**
     * 显示选取图片dialog
     */
    private void showChoosePictureDialog() {
        if (mChoosePicturedialog == null) {
            mChoosePicturedialog = new ChoosePictureDialog(getActivity(), R.style.common_dialog_style);
        }
        mChoosePicturedialog.show();
    }

    public class ChoosePictureDialog extends Dialog implements DialogInterface.OnDismissListener {

        @Bind(R.id.bt_choose_picture_from_gallary)
        Button btChoosePictureFromGallary;
        @Bind(R.id.bt_choose_picture_camera)
        Button btChoosePictureCamera;
        @Bind(R.id.bt_cancle)
        Button btCancle;

        public ChoosePictureDialog(Context context, int theme) {
            super(context, theme);
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
                    choseHeadImageFromGallery();
                    dismiss();
                }
            });
            btChoosePictureCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //相机拍照
                    choseHeadImageFromCameraCapture();
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


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        userPresenter = new UserCenterPresenter(this);
//        try {
//            mListener = (OnUserCenterFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnUserCenterFragmentInteractionListener");
//        }
    }

    @Override
    public void onResume() {
        userPresenter.getUserInfo();
        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        userPresenter.onDestory();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setUserHeadImage(String url) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        Glide.with(getActivity()).load(url).into(ivUserHeadimage);
    }

    @Override
    public void setUserNickName(String nickName) {
        if (TextUtils.isEmpty(nickName))
            return;
        tvUserNickname.setText(nickName);
    }

    @Override
    public void setUserVipLevel(int level) {
        tvVipLevel.setText("VIP" + level);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnUserCenterFragmentInteractionListener {
        /**
         * 用户中心的头像被点击
         */
        void onUserHeadImageViewClicked();
    }

}
