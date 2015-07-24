package com.fxft.cheyoufuwu.common.util;

import java.text.DecimalFormat;

/**
 * Created by taosj on 2015/7/21.
 */
public class StringFormatUtil {

    /**
     * 保留小数点后两位
     * @param number 数值
     * @return
     */
    public static String afterDecimalTwo(double number){
        DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        return decimalFormat.format(number);//format 返回的是字符串
    }

}
