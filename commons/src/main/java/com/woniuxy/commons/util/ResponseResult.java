package com.woniuxy.commons.util;

import lombok.Data;

/**
 * @author: QFX
 * DateTime: 2022-05-17 11:43
 * Description:
 */
@Data
public class ResponseResult<T> {
    private int code;
    private String msg;
    private T data;
    private ResStatus status;   //响应状态，前端以此判断
}
