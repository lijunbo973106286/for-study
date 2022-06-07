package com.woniuxy.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * \* @author: ZJH
 * \* @DateTime: 2022-05-17 11:42
 * \* @Description：统一请求响应结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {
    /**
     * 状态码，参考http状态码
     */
    private int code;
    /**
     * 返回的消息
     */
    private String message;
    /**
     * 返回的数据
     */
    private T data;
    /**
     * 响应状态，前端以此作为判断依据进行下一步操作
     */
    private ResStatus status;

    public static final ResponseResult<Object> SUCCESS = new ResponseResult<>(200, "执行成功", null, ResStatus.SUCCESS);
    public static final ResponseResult<Object> FAIL = new ResponseResult<>(500, "执行失败", null, ResStatus.FAIL);
}
