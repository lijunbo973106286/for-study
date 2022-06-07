package com.woniuxy.commons.util;

import lombok.Data;

/**
 * @author: QFX
 * DateTime: 2022-05-17 11:43
 * Description:
 */a
@Data
public class ResponseResult<T> {
    private int code;
    private String msg;
    private T data;
    private ResStatus status;   //响应状态，前端以此判断
    private static ResponseResult responseResult;
    public static ResponseResult getResponseResult(){
        if (responseResult!=null){
            synchronized (ResponseResult.class){
                if (responseResult!=null){
                    responseResult=new ResponseResult();
                }
            }
        }
        return responseResult;
    }
    public ResponseResult success(T data){
        responseResult.setStatus(ResStatus.SUCCESS);
        responseResult.setCode(200);
        responseResult.setData(data);
        responseResult.setMsg("成功");
        return responseResult;
    }
    public ResponseResult fail(T data){
        responseResult.setStatus(ResStatus.FAIL);
        responseResult.setCode(500);
        responseResult.setData(data);
        responseResult.setMsg("失败");
        return responseResult;
    }


}
