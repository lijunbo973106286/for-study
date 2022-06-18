package com.woniuxy.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.entity
 * @className: Register
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/6/15 21:10
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register {
    String uname;
    String name;
    String password;
    String email;
    String phonenum;
    String identity;
    int corpId;
    int type;
    String corpname;
    String credit_code;
    String legal_person;
    String id_card;
    String address;
}
