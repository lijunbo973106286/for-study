package com.woniuxy.commons.util;

import java.text.SimpleDateFormat;

/**
 * \* @author: ZJH
 * \* @DateTime: 2022-06-07 19:53
 * \* @Description：
 */
public class ConvertTime {
    public static String getTime(String date){
        /** 设置转换格式 */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /** 转换当前时间 */
        return sdf.format(date);
    }
}
