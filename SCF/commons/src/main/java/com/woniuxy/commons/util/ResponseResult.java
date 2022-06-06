package com.woniuxy.commons.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: springcloud86
 * @package: com.woniuxy.commons.util
 * @className: ResponseResult
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/5/17 11:43
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {
    int code; //状态码
    String msg; //消息
    T data; //数据
    ResStatus status;//响应状态,前端判断的依据，以此进行下一步操作
}
