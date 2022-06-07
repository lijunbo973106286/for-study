package com.woniuxy.supply.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniuxy.commons.entity.DTO.SupplyDTO;
import com.woniuxy.commons.entity.PageInfomation;
import com.woniuxy.commons.entity.ResStatus;
import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.supply.dao.SuppluDao;
import com.woniuxy.supply.service.SupplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @BelongsProject supply-chain-finance
 * @BelongsPackage com.woniuxy.supply.service.impl
 * @Author qfx
 * @CreateTime 2022-06-07  11:18
 * @Description TODO
 * @Version 1.0
 */
@Slf4j
@Service
public class SupplyServiceImpl implements SupplyService {
    @Resource
    SuppluDao suppluDao;
    private PageInfo<SupplyDTO> info;

    @Override
    public ResponseResult findAllSupply(PageInfomation pageInfomation) {
        int currentPage = pageInfomation.getCurrentPage();
        int pageSize = pageInfomation.getPageSize();
        PageHelper.startPage(currentPage, pageSize);
        List<SupplyDTO> all = suppluDao.findAllSupply();
        if (all.isEmpty()) {
            return new ResponseResult(500, "查询失败", null, ResStatus.FAIL);
        } else {
            log.info("所有供应链的核心企业：{}", all);
            for (SupplyDTO supplyDTO : all
            ) {
                int num = recursiveMax(supplyDTO.getEnterprises());
                supplyDTO.setTier(num);
                log.info("层级：{}", num);
            }
            PageInfo<SupplyDTO> info = PageInfo.of(all);
            return new ResponseResult(200, "查询成功", info, ResStatus.SUCCESS);
        }
    }

    /**
     * @param list
     * @return int
     * @description 递归供应链层级
     * @author qfx
     * @date 2022/6/7 15:52
     */
    public int recursiveMax(List list) {
        if (list == null) {
            return 1;
        }
        boolean flag = false;
        ArrayList<Integer> num = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            SupplyDTO supplyDTO = (SupplyDTO) list.get(i);
            if (supplyDTO.getEnterprises() != null) {
                flag = true;
                num.add(recursiveMax(supplyDTO.getEnterprises()));
            }
        }
        if (flag) {
            return Collections.max(num) + 1;
        } else {
            return 1;
        }
    }

}
