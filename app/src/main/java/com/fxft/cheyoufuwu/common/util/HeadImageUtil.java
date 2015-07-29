package com.fxft.cheyoufuwu.common.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;

/**
 * Created by ChenDJ on 2015/7/29.<br>
 */
public class HeadImageUtil {

    /* 请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    /* 头像文件 */
    private static final String IMAGE_FILE_NAME = "temp_head_image.jpg";

    // 裁剪后图片的宽(X)和高(Y),480 X 480的正方形。
    private static int output_X = 480;
    private static int output_Y = 480;
    private static OnImageSuccessListener mListener;

    public interface OnImageSuccessListener{
        void onGetPhoto(Bitmap photo);
    }

    public static void setOnImageSuccessListener(OnImageSuccessListener listener){
        mListener = listener;
    }

    public static void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        // 用户没有进行有效的设置操作，返回
        if (resultCode == activity.RESULT_CANCELED) {
            return;
        }
        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                cropRawPhoto(activity,data.getData());
                break;

            case CODE_CAMERA_REQUEST:
                if (SystemUtil.hasSdcard()) {
                    File tempFile = new File(
                            Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME);
                    cropRawPhoto(activity,Uri.fromFile(tempFile));
                }
                break;

            case CODE_RESULT_REQUEST:
                if (data != null) {
                    setImageToHeadView(data);
                }
                break;
        }
    }

    /**
     * 到本地相册选取图片
     */
    public static void choseHeadImageFromGallery(Activity activity) {
        Intent intentFromGallery = new Intent();
        // 设置文件类型
        intentFromGallery.setType("image/*");
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
    }

    /**
     * Chose head image from camera capture.
     */
    public static void choseHeadImageFromCameraCapture(Activity activity) {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 判断存储卡是否可用，存储照片文件
        if (SystemUtil.hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                    .fromFile(new File(Environment
                            .getExternalStorageDirectory(), IMAGE_FILE_NAME)));
        }

        activity.startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);
    }

    /**
     * 裁剪原始的图片
     */
    private static void cropRawPhoto(Activity activity,Uri uri) {

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

        activity.startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private static void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            final Bitmap photo = extras.getParcelable("data");
            if (mListener != null){
                mListener.onGetPhoto(photo);
            }
        }
    }
}
