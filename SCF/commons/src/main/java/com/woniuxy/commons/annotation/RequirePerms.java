package com.woniuxy.commons.annotation;

import java.lang.annotation.*;

/**
 * @projectName: springcloud86
 * @package: com.woniuxy.commons.annotation
 * @className: RequirePerms
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/5/19 10:17
 * @version: 1.0
 */
@Documented
@Target(ElementType.METHOD) //方法上
@Retention(RetentionPolicy.RUNTIME) //运行时获取
public @interface RequirePerms {
    String value();
}
