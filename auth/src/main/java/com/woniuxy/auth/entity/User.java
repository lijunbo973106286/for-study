package com.woniuxy.auth.entity;

import lombok.Data;

/**
 * @BelongsProject: myspringcloud
 * @BelongsPackage: com.woniuxy.auth.entity
 * @Author: qfx
 * @CreateTime: 2022-05-18  16:28
 * @Description: 用户类
 * @Version: 1.0
 */
@Data
public class User {
    private String uname;
    private String password;
}
