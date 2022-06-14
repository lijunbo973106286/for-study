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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

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
    public ResponseResult findAllNetwork(NetworkDTO networkDTO) {
        int currentPage = networkDTO.getCurrentPage();
        int pageSize = networkDTO.getPageSize();
        PageHelper.startPage(currentPage, pageSize);
        List<NetworkDTO> all = networkDao.findAllNetwork(networkDTO);
        if (all.isEmpty()) {
            return new ResponseResult(500, "查询失败", null, ResStatus.FAIL);
        } else {
            for (NetworkDTO networkDTO_ : all
            ) {
                List<ScfpEnterprise> enterprises = networkDao.findByNid(networkDTO_.getId());
                int num = enterprises.size();
                networkDTO_.setEnterprises(enterprises);
                networkDTO_.setNum(num);
                ArrayList<Integer> eids = new ArrayList<>();
                if (!enterprises.isEmpty()) {
                    for (ScfpEnterprise enterprise : enterprises
                    ) {
                        log.info("所有关联企业：{}", enterprise);
                        if (enterprise != null) {
                            int eid = enterprise.getId();
                            eids.add(eid);
                        }
                    }
                    networkDTO_.setEids(eids);
                }
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
            //添加流转网络包含的子企业
            List<Integer> eids = networkDTO.getEids();
            int nid = networkDTO.getId();
            ArrayList<Integer> eids_ = new ArrayList<>(new TreeSet<Integer>(eids));
            for (int eid : eids_
            ) {
                networkDao.addNetworkEnterprise(nid, eid);
            }
            //添加流转网络对应的核心企业，一个核心企业对应多个流转网络
            int coreid = networkDTO.getCoreId();
            networkDao.addNetworkCore(nid, coreid);
            return new ResponseResult(200, "添加成功", null, ResStatus.SUCCESS);
        }
    }

    @Transactional
    @Override
    public ResponseResult updateNetwork(NetworkDTO networkDTO) {
        log.info("修改流转网络添加入参：{}", networkDTO);
        int i = networkDao.updateNetwork(networkDTO);
        if (i <= 0) {
            return new ResponseResult(500, "修改失败", null, ResStatus.FAIL);
        } else {
            List<Integer> eids = networkDTO.getEids();
            int nid = networkDTO.getId();
            networkDao.deleteNetworkEnterprise(nid);
            ArrayList<Integer> eids_ = new ArrayList<>(new TreeSet<Integer>(eids));
            for (int eid : eids_
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

    @Override
    public ResponseResult updateStatus(NetworkDTO networkDTO) {
        log.info("流转网络添加入参：{}", networkDTO);
        if ("1".equals(networkDTO.getStatus())) {
            networkDTO.setStatus("0");
        } else {
            networkDTO.setStatus("1");
        }
        int i = networkDao.updateStatus(networkDTO);
        if (i <= 0) {
            return new ResponseResult(500, "修改失败", null, ResStatus.FAIL);
        } else {
            return new ResponseResult(200, "修改成功", null, ResStatus.SUCCESS);
        }
    }

    @Override
    public ResponseResult findByCoreId(int coreId) {
        log.info("核心企业id：{}", coreId);
        List<NetworkDTO> all = networkDao.findByCoreId(coreId);
        if (all.isEmpty()) {
            return new ResponseResult(500, "查询失败", null, ResStatus.FAIL);
        } else {
            return new ResponseResult(200, "查询成功", all, ResStatus.SUCCESS);
        }
    }

}
