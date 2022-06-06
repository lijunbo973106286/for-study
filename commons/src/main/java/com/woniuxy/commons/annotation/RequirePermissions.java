package com.woniuxy.commons.annotation;

import java.lang.annotation.*;

/**
 * @BelongsProject: myspringcloud
 * @BelongsPackage: com.woniuxy.commons.annotation
 * @Author: qfx
 * @CreateTime: 2022-05-19  10:17
 * @Description: 自定义权限注解
 * @Version: 1.0
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequirePermissions {
    String value();
}
