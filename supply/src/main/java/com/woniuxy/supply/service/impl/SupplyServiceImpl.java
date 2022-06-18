package com.woniuxy.supply.service.impl;

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

    ArrayList<SupplyDTO> supplyDTOS = new ArrayList<>();

    @Override
    public List<SupplyDTO> findAll() {

        List<SupplyDTO> all = suppluDao.findAllSupply();
        if (all.isEmpty()) {
            return null;
        } else {
            for (SupplyDTO supplyDTO : all
            ) {
                int num = recursiveMax(supplyDTO.getEnterprises());
                supplyDTO.setTier(num);
                log.info("层级：{}", num);
                int num1 = getNum(supplyDTO);
                log.info("下级客户数量：{}", num1);
                supplyDTO.setNum(num1);
            }
            return all;
        }
    }

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

    @Override
    public ResponseResult findByEid(SupplyDTO supplyDTO) {
        int currentPage = supplyDTO.getCurrentPage();
        int pageSize = supplyDTO.getPageSize();
        PageHelper.startPage(currentPage, pageSize);
        int eid = supplyDTO.getEid();
        List<SupplyDTO> all = suppluDao.findById(eid);
        if (all.isEmpty()) {
            return new ResponseResult(500, "查询失败", null, ResStatus.FAIL);
        } else {
            log.info("企业对应的供应链：{}", all);
            PageInfo<SupplyDTO> info = PageInfo.of(all.get(0).getEnterprises());
            return new ResponseResult(200, "查询成功", info, ResStatus.SUCCESS);
        }
    }

    @Override
    public ResponseResult findByConditionAndEid(SupplyDTO supplyDTO) {
        log.info("条件：{}", supplyDTO);
        int currentPage = supplyDTO.getCurrentPage();
        int pageSize = supplyDTO.getPageSize();
        PageHelper.startPage(currentPage, pageSize);
        List<SupplyDTO> all = suppluDao.findByConditionAndEid(supplyDTO);
        if (all.isEmpty()) {
            return new ResponseResult(500, "查询失败", null, ResStatus.FAIL);
        } else {
            log.info("根据id条件查询供应链：{}", all);
            PageInfo<SupplyDTO> info = PageInfo.of(all);
            return new ResponseResult(200, "查询成功", info, ResStatus.SUCCESS);
        }
    }

    @Override
    public ResponseResult findAllEnterprises(SupplyDTO supplyDTO) {
        log.info("条件：{}", supplyDTO);
        List<SupplyDTO> list = suppluDao.findById(supplyDTO.getEid());
        List<SupplyDTO> list1 = suppluDao.findByFid(supplyDTO.getEid());
        List<SupplyDTO> list_ = getSupplyDTO(list);
        List<SupplyDTO> list3 = getSupplyDTO(list1);
        if (list3 != null) {
            list_.addAll(list3);
        }
        List<SupplyDTO> all = suppluDao.findAllEnterprises();
//        all.removeAll(distinct(list_));
        List<SupplyDTO> resultList = all.stream()
                .filter(item -> !distinct(list_).stream().map(e -> e.getEid()).collect(Collectors.toList()).contains(item.getEid()))
                .collect(Collectors.toList());
        if (all.isEmpty()) {
            return new ResponseResult(500, "查询失败", null, ResStatus.FAIL);
        } else {
            log.info("非供应链企业：{}", all);
            return new ResponseResult(200, "查询成功", resultList, ResStatus.SUCCESS);
        }
    }

    @Override
    public ResponseResult add(SupplyDTO supplyDTO) {
        List<SupplyDTO> enterprises = supplyDTO.getEnterprises();
        int i = 0;
        for (SupplyDTO enterprise : enterprises
        ) {
            enterprise.setCount(1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            enterprise.setUpdate_time(sdf.format(new Date()));
            enterprise.setFid(supplyDTO.getFid());
            log.info("添加入参：{}", enterprise);
            SupplyDTO supplyDTO1 = suppluDao.exist(enterprise);
            if (supplyDTO1 == null) {
                i += suppluDao.add(enterprise);
            } else {
                int count = supplyDTO1.getCount();
                enterprise.setCount(++count);
                int j = suppluDao.update(enterprise);
                if (j > 0) {
                    return new ResponseResult(200, "更新成功", null, ResStatus.SUCCESS);
                }
            }
        }
        if (i <= 0) {
            return new ResponseResult(500, "邀请失败", null, ResStatus.FAIL);
        } else {
            return new ResponseResult(200, "邀请成功", null, ResStatus.SUCCESS);
        }
    }

    @Override
    public ResponseResult delete(int fid, int eid) {
        int i = suppluDao.delete(fid, eid);
        log.info("删除入参：{}，{}", fid, eid);
        if (i <= 0) {
            return new ResponseResult(500, "移除失败", null, ResStatus.FAIL);
        } else {
            return new ResponseResult(200, "移除成功", null, ResStatus.SUCCESS);
        }
    }

    @Override
    public ResponseResult findAllInvite(SupplyDTO supplyDTO) {
        int currentPage = supplyDTO.getCurrentPage();
        int pageSize = supplyDTO.getPageSize();
        PageHelper.startPage(currentPage, pageSize);
        int eid = supplyDTO.getEid();
        List<SupplyDTO> all = suppluDao.findAllInvite(eid);
        if (all.isEmpty()) {
            return new ResponseResult(500, "查询失败", null, ResStatus.FAIL);
        } else {
            PageInfo<SupplyDTO> info = PageInfo.of(all);
            return new ResponseResult(200, "查询成功", info, ResStatus.SUCCESS);
        }
    }

    @Override
    public ResponseResult aggre(int fid, int eid) {
        int i = suppluDao.updateStatus(fid, eid);
        if (i > 0) {
            return new ResponseResult(200, "同意成功", null, ResStatus.SUCCESS);
        } else {
            return new ResponseResult(500, "同意失败", null, ResStatus.FAIL);
        }
    }

    @Override
    public ResponseResult refuse(int fid, int eid) {
        int i = suppluDao.delete(fid, eid);
        log.info("拒绝入参：{}，{}", fid, eid);
        if (i <= 0) {
            return new ResponseResult(500, "移除失败", null, ResStatus.FAIL);
        } else {
            return new ResponseResult(200, "移除成功", null, ResStatus.SUCCESS);
        }
    }

    @Override
    public ResponseResult findByInvite(SupplyDTO supplyDTO) {
        int currentPage = supplyDTO.getCurrentPage();
        int pageSize = supplyDTO.getPageSize();
        PageHelper.startPage(currentPage, pageSize);
        List<SupplyDTO> all = suppluDao.findByInvite(supplyDTO);
        if (all.isEmpty()) {
            return new ResponseResult(500, "查询失败", null, ResStatus.FAIL);
        } else {
            PageInfo<SupplyDTO> info = PageInfo.of(all);
            return new ResponseResult(200, "查询成功", info, ResStatus.SUCCESS);
        }
    }


    /**
     * @param supplyDTOList
     * @return List<SupplyDTO>
     * @description 递归查询供应链上的所有企业
     * @author qfx
     * @date 2022/6/13 19:26
     */
    public List<SupplyDTO> getSupplyDTO(List<SupplyDTO> supplyDTOList) {
        if (supplyDTOList.isEmpty()) {
            return null;
        } else {
            for (SupplyDTO s1 : supplyDTOList
            ) {
                supplyDTOS.add(s1);
                if (s1.getEnterprises() == null) {
                    return null;
                } else {
                    getSupplyDTO(s1.getEnterprises());
                }
            }
        }
        return supplyDTOS;
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

    /**
     * @param list
     * @return List<SupplyDTO>
     * @description 去重
     * @author qfx
     * @date 2022/6/13 19:41
     */

    public List<SupplyDTO> distinct(List<SupplyDTO> list) {
        final boolean sta = null != list && list.size() > 0;
        List doubleList = new ArrayList();
        if (sta) {
            Set set = new HashSet();
            set.addAll(list);
            doubleList.addAll(set);
        }
        return doubleList;
    }

}
