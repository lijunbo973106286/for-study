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
                int num1 = getNum(supplyDTO);
                log.info("下级客户数量：{}", num1);
                supplyDTO.setNum(num1);
            }
            PageInfo<SupplyDTO> info = PageInfo.of(all);
            return new ResponseResult(200, "查询成功", info, ResStatus.SUCCESS);
        }
    }

    @Override
    public ResponseResult findById(int coreId) {
        List<SupplyDTO> all = suppluDao.findById(coreId);
        if (all.isEmpty()) {
            return new ResponseResult(500, "查询失败", null, ResStatus.FAIL);
        } else {
            log.info("核心企业对应的供应链：{}", all);
            return new ResponseResult(200, "查询成功", all, ResStatus.SUCCESS);
        }
    }

    @Override
    public ResponseResult findByCondition(SupplyDTO supplyDTO) {
        int currentPage = supplyDTO.getCurrentPage();
        int pageSize = supplyDTO.getPageSize();
        PageHelper.startPage(currentPage, pageSize);
        List<SupplyDTO> all = suppluDao.findByCondtion(supplyDTO);
        if (all.isEmpty()) {
            return new ResponseResult(500, "查询失败", null, ResStatus.FAIL);
        } else {
            log.info("条件查询供应链的核心企业：{}", all);
            for (SupplyDTO supplyDTO_ : all
            ) {
                int num = recursiveMax(supplyDTO_.getEnterprises());
                supplyDTO_.setTier(num);
                log.info("层级：{}", num);
                int num1 = getNum(supplyDTO_);
                log.info("下级客户数量：{}", num1);
                supplyDTO_.setNum(num1);
            }
            PageInfo<SupplyDTO> info = PageInfo.of(all);
            return new ResponseResult(200, "查询成功", info, ResStatus.SUCCESS);
        }
    }

    /**
     * @param
     * @return int
     * @description 递归供应链企业数量
     * @author qfx
     * @date 2022/6/7 15:52
     */
    public int getNum(SupplyDTO supplyDTO) {
        if (supplyDTO.getEnterprises() == null) {
            return 0;
        }
        int num = 0;
        List<SupplyDTO> enterprises = supplyDTO.getEnterprises();
        num += enterprises.size();
        for (SupplyDTO enterprise : enterprises
        ) {
            num += getNum(enterprise);
        }
        return num;
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
