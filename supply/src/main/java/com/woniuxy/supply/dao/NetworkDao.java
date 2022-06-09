package com.woniuxy.supply.dao;

import com.woniuxy.commons.entity.DTO.NetworkDTO;
import com.woniuxy.commons.entity.ScfpEnterprise;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @BelongsProject supply-chain-finance
 * @BelongsPackage com.woniuxy.supply.dao
 * @Author qfx
 * @CreateTime 2022-06-07  16:30
 * @Description TODO
 * @Version 1.0
 */
public interface NetworkDao {
    List<NetworkDTO> findAllNetwork();

    List<ScfpEnterprise> findByNid(int nid);

    int add(NetworkDTO networkDTO);

    int addNetworkEnterprise(@Param("nid")int nid,@Param("eid")int eid);

    int updateNetwork(NetworkDTO networkDTO);

    int deleteNetworkEnterprise(int nid);

    List<NetworkDTO> findByCondition(NetworkDTO networkDTO);

    int updateStatus(NetworkDTO networkDTO);

    void addNetworkCore(@Param("nid")int nid,@Param("coreid")int coreid);

    List<NetworkDTO> findByCoreId(int coreId);
}
