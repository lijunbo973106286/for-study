package com.woniuxy.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: springcloud86
 * @package: com.woniuxy.auth.entity
 * @className: User
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/5/18 16:33
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String uname;
    String password;
}
