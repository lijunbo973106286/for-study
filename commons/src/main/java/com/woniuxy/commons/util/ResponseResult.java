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
    private static ResponseResult responseResult;
    private int code;
    private String msg;
    private T data;
    private ResStatus status;   //响应状态，前端以此判断
    public static ResponseResult getResponseResult(){
        if (responseResult==null){
            synchronized (ResponseResult.class){
                if (responseResult==null){
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
    public ResponseResult fail(){
        responseResult.setStatus(ResStatus.FAIL);
        responseResult.setCode(500);
        responseResult.setData(null);
        responseResult.setMsg("失败");
        return responseResult;
    }


}
