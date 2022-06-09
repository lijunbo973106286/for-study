package com.woniuxy.supply.dao;

import com.woniuxy.commons.entity.DTO.SupplyDTO;
import org.apache.ibatis.annotations.Mapper;

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
    public List<SupplyDTO> findAllSupply();

    List<SupplyDTO> findById(int eid);

    List<SupplyDTO> findByCondtion(SupplyDTO supplyDTO);

    List<SupplyDTO> findByConditionAndEid(SupplyDTO supplyDTO);
}
