package com.woniuxy.commons.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * \* @author: ZJH
 * \* @DateTime: 2022-06-07 19:53
 * \* @Description：
 */
public class ConvertTIme {
    public static String getTime(){
        /** 设置转换格式 */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /** 转换当前时间 */
        return sdf.format(date);
    }
}
