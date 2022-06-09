package com.woniuxy.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.entity
 * @className: ScfpUserRole
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/6/8 10:46
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScfpUserRole {
    int user_id;
    List<Integer> role_id;
}
