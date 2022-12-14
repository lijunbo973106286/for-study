package com.woniuxy.supply.controller;

import com.woniuxy.commons.entity.DTO.NetworkDTO;
import com.woniuxy.commons.entity.PageInfomation;
import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.supply.service.NetworkService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @BelongsProject supply-chain-finance
 * @BelongsPackage com.woniuxy.supply.controller
 * @Author qfx
 * @CreateTime 2022-06-07  16:07
 * @Description 链单流转网络管理
 * @Version 1.0
 */
@RestController
@RequestMapping("/network")
public class NetworkController {
    @Resource
    NetworkService networkService;

    /**
     * @param
     * @return ResponseResult
     * @description 查询所有流转网络
     * @author qfx
     * @date 2022/6/7 19:41
     */
    @PostMapping("/all")
    public ResponseResult findAllNetwork(@RequestBody NetworkDTO networkDTO) {
        return networkService.findAllNetwork(networkDTO);
    }

    /**
     * @param networkDTO
     * @return ResponseResult
     * @description 添加一个流转网络
     * @author qfx
     * @date 2022/6/7 19:42
     */
    @PostMapping("/add")
    public ResponseResult addNetwork(@RequestBody NetworkDTO networkDTO) {
        return networkService.addNetwork(networkDTO);
    }

    /**
     * @param networkDTO
     * @return ResponseResult
     * @description 根据id修改某个流转网络
     * @author qfx
     * @date 2022/6/7 19:42
     */
    @PostMapping("/update")
    public ResponseResult updateNetwork(@RequestBody NetworkDTO networkDTO) {
        return networkService.updateNetwork(networkDTO);
    }

    /**
     * @param networkDTO
     * @return ResponseResult
     * @description 条件查询流转网络
     * @author qfx
     * @date 2022/6/7 19:42
     */
    @PostMapping("/findBy")
    public ResponseResult findByCondition(@RequestBody NetworkDTO networkDTO) {
        return networkService.findByCondition(networkDTO);
    }

    /**
     * @param networkDTO
     * @return ResponseResult
     * @description 修改流转网络状态
     * @author qfx
     * @date 2022/6/7 19:42
     */
    @PostMapping("/updateStatus")
    public ResponseResult updateStatus(@RequestBody NetworkDTO networkDTO) {
        return networkService.updateStatus(networkDTO);
    }

    /**
     * @param coreId
     * @return ResponseResult
     * @description 查询当前登录企业可用的流转网络
     * @author qfx
     * @date 2022/6/10 15:28
     */
    @GetMapping("/findByCoreId/{coreId}")
    public ResponseResult findByCoreId(@PathVariable("coreId") int coreId) {
        return networkService.findByCoreId(coreId);
    }

    /**
     * @param nid
     * @return ResponseResult
     * @description 根据流转网络id查询关联企业
     * @author qfx
     * @date 2022/6/14 16:57
     */
    @GetMapping("/findByNid/{nid}")
    public ResponseResult findByNid(@PathVariable("nid") int nid) {
        return networkService.findByNid(nid);
    }

}
