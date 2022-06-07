package com.woniuxy.supply.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniuxy.commons.entity.DTO.NetworkDTO;
import com.woniuxy.commons.entity.PageInfomation;
import com.woniuxy.commons.entity.ResStatus;
import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpEnterprise;
import com.woniuxy.commons.util.SnowflakeDistributeId;
import com.woniuxy.supply.dao.NetworkDao;
import com.woniuxy.supply.service.NetworkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject supply-chain-finance
 * @BelongsPackage com.woniuxy.supply.service.impl
 * @Author qfx
 * @CreateTime 2022-06-07  16:13
 * @Description TODO
 * @Version 1.0
 */
@Service
@Slf4j
public class NetworkServiceImpl implements NetworkService {
    SnowflakeDistributeId idWorker = new SnowflakeDistributeId(0, 0);
    @Resource
    NetworkDao networkDao;

    @Override
    public ResponseResult findAllNetwork(PageInfomation pageInfomation) {
        int currentPage = pageInfomation.getCurrentPage();
        int pageSize = pageInfomation.getPageSize();
        PageHelper.startPage(currentPage, pageSize);
        List<NetworkDTO> all = networkDao.findAllNetwork();
        if (all.isEmpty()) {
            return new ResponseResult(500, "查询失败", null, ResStatus.FAIL);
        } else {
            for (NetworkDTO networkDTO : all
            ) {
                List<ScfpEnterprise> enterprises = networkDao.findByNid(networkDTO.getId());
                int num = enterprises.size();
                networkDTO.setEnterprises(enterprises);
                networkDTO.setNum(num);
            }
            log.info("所有流转网络：{}", all);
            PageInfo<NetworkDTO> info = PageInfo.of(all);
            return new ResponseResult(200, "查询成功", info, ResStatus.SUCCESS);
        }
    }

    @Transactional
    @Override
    public ResponseResult addNetwork(NetworkDTO networkDTO) {
        long network_no = idWorker.nextId();
        networkDTO.setNetwork_no("nt" + network_no);
        log.info("流转网络添加入参：{}", networkDTO);
        int i = networkDao.add(networkDTO);
        if (i <= 0) {
            return new ResponseResult(500, "添加失败", null, ResStatus.FAIL);
        } else {
            List<Integer> eids = networkDTO.getEids();
            int nid = networkDTO.getId();
            for (int eid : eids
            ) {
                networkDao.addNetworkEnterprise(nid, eid);
            }
            return new ResponseResult(200, "添加成功", null, ResStatus.SUCCESS);
        }
    }

    @Transactional
    @Override
    public ResponseResult updateNetwork(NetworkDTO networkDTO) {
        log.info("流转网络添加入参：{}", networkDTO);
        int i = networkDao.updateNetwork(networkDTO);
        if (i <= 0) {
            return new ResponseResult(500, "修改失败", null, ResStatus.FAIL);
        } else {
            List<Integer> eids = networkDTO.getEids();
            int nid = networkDTO.getId();
            networkDao.deleteNetworkEnterprise(nid);
            for (int eid : eids
            ) {
                networkDao.addNetworkEnterprise(nid, eid);
            }
            return new ResponseResult(200, "修改成功", null, ResStatus.SUCCESS);
        }
    }

    @Override
    public ResponseResult findByCondition(NetworkDTO networkDTO) {
        log.info("流转网络查询入参：{}", networkDTO);
        int currentPage = networkDTO.getCurrentPage();
        int pageSize = networkDTO.getPageSize();
        PageHelper.startPage(currentPage, pageSize);
        List<NetworkDTO> all = networkDao.findByCondition(networkDTO);
        if (all.isEmpty()) {
            return new ResponseResult(500, "查询失败", null, ResStatus.FAIL);
        } else {
            for (NetworkDTO networkDTO_ : all
            ) {
                List<ScfpEnterprise> enterprises = networkDao.findByNid(networkDTO_.getId());
                int num = enterprises.size();
                networkDTO_.setEnterprises(enterprises);
                networkDTO_.setNum(num);
            }
            log.info("所有流转网络：{}", all);
            PageInfo<NetworkDTO> info = PageInfo.of(all);
            return new ResponseResult(200, "查询成功", info, ResStatus.SUCCESS);
        }
    }

}
