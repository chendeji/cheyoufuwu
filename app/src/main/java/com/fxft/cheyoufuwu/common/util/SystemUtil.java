package com.fxft.cheyoufuwu.common.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.os.Environment;
import android.util.TypedValue;


import com.fxft.cheyoufuwu.R;

import java.util.List;

/**
 * Created by chendeji on 2/7/15.
 */
public class SystemUtil {

    /**
     * @param context the context
     * @return the boolean
     */
    public static boolean isLastActivityInTask(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
        if (runningTaskInfos != null) {
            for (ActivityManager.RunningTaskInfo info : runningTaskInfos) {
                String packageName = info.topActivity.getPackageName();
                if (context.getPackageName().equalsIgnoreCase(packageName)) {
                    int count = info.numActivities;
                    if (count == 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 检查一个APP是否安装
     *
     * @param context the context
     * @param packageName the package name
     * @return the boolean
     */
    public static boolean isAppInstalled(Context context, String packageName){
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager()
                    .getApplicationInfo(packageName,
                            PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 获取actionbar高度
     *
     * @param context the context
     * @return the action bar size
     */
    public static int getActionBarSize(Context context) {
        TypedValue typedValue = new TypedValue();
        int[] textSizeAttr = new int[]{R.attr.actionBarSize};
        int indexOfAttrTextSize = 0;
        TypedArray a = context.obtainStyledAttributes(typedValue.data, textSizeAttr);
        int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
        return actionBarSize;
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }

}
