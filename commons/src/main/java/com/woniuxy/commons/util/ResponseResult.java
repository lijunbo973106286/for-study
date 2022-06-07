package com.woniuxy.commons.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: QFX
 * DateTime: 2022-05-17 11:43
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {
    private int code;
    private String msg;
    private T data;
    private ResStatus status;   //响应状态，前端以此判断
}
