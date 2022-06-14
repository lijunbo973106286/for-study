package com.woniuxy.supply.dao;

import com.woniuxy.commons.entity.DTO.SupplyDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @BelongsProject supply-chain-finance
 * @BelongsPackage com.woniuxy.supply.dao
 * @Author qfx
 * @CreateTime 2022-06-07  11:25
 * @Description TODO
 * @Version 1.0
 */
public interface SuppluDao {

    List<SupplyDTO> findAllSupply();

    List<SupplyDTO> findById(int eid);

    List<SupplyDTO> findByCondtion(SupplyDTO supplyDTO);

    List<SupplyDTO> findByConditionAndEid(SupplyDTO supplyDTO);

    List<SupplyDTO> findAllEnterprises();

    int add(SupplyDTO supplyDTO);

    int delete(@Param("fid") int fid, @Param("eid") int eid);

    List<SupplyDTO> findAllInvite(int eid);

    SupplyDTO exist(SupplyDTO enterprise);

    int update(SupplyDTO enterprise);

    int updateStatus(@Param("fid") int fid, @Param("eid") int eid);

    List<SupplyDTO> findFid(SupplyDTO enterprise);

    List<SupplyDTO> findByInvite(SupplyDTO supplyDTO);

    List<SupplyDTO> findByFid(int eid);
}
