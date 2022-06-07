package com.woniuxy.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: QFX
 * DateTime: 2022-04-25 18:51
 * Description:用于封装分页信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfomation {
    private int currentPage;
    private int pageSize;
}
