package com.woniuxy.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.entity
 * @className: ScfpUserDTO
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/6/13 18:17
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScfpUserDTO extends ScfpUser{
    List<ScfpUserRole> role_ids;
}
