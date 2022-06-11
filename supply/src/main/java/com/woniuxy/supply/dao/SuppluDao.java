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

    List<SupplyDTO> findAllEnterprises(SupplyDTO supplyDTO);

    int add(@Param("enterprises") List<SupplyDTO> enterprises, @Param("fid") int fid);

    int delete(@Param("fid") int fid,@Param("eid") int eid);
}
