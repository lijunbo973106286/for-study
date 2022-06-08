package com.woniuxy.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.entity
 * @className: ScfpRoleMenu
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/6/8 10:45
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScfpRoleMenu {
    int role_id;
    List menu_id;
}
