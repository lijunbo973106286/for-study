package com.woniuxy.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.entity
 * @className: EnterpriseDTO
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/6/15 16:17
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnterpriseDTO extends ScfpEnterprise {
    int type;
    String typeName;
    String pName;
    String pPhonenum;
    String pEmail;
    String pType;

}
