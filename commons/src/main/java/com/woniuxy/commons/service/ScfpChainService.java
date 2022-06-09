package com.woniuxy.commons.service;

import com.woniuxy.commons.entity.ResponseResult;
import com.woniuxy.commons.entity.ScfpChain;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * \* @author: ZJH
 * \* @DateTime: 2022-06-07 10:28
 * \* @Description：
 */
@FeignClient(name = "chain")
public interface ScfpChainService {

    /** 通过传入的chain对象插入scfp_chain表中，没有的信息默认为null或者0 */
    @PostMapping("/chain/insert")
    public ResponseResult<Object> insert(@RequestBody ScfpChain scfpChain);

    /**
     * 通过传入chain对象动态修改scfp_chain表中的信息
     */
    @PutMapping("/chain/update")
    public ResponseResult<Object> update(@RequestBody ScfpChain scfpChain);
    /** 通过id进行软删除*/
    @DeleteMapping("/chain/delete/{id}")
    public  ResponseResult<Object> delete(@PathVariable("id") int id);

    /** 通过scfpchain对象动态查询ScfpChain对象*/
    @PostMapping("/chain/search")
    public ResponseResult<ScfpChain> search(@RequestBody ScfpChain scfpChain);

    /** 分页查询所有链单信息 */
    @PostMapping("/chain/findAll")
    public ResponseResult<Object> findAll(@RequestBody ScfpChain scfpChain);

    /** 查找所有链单数量 */
    @GetMapping("/chain/findAllCount")
    public ResponseResult<Object> findAllCount();
    /** 查找各分类链单数量 */
    @GetMapping("/chain/findCount/{status}")
    public ResponseResult<Object> findCount(@PathVariable("status") String status);

   /* @PostMapping("/upload/{idnum}")
    public ResponseResult<ScfpFile> upload(MultipartFile file, @PathVariable("idnum") int idnum) throws IOException {
        return scfpFileService.upload(file,idnum);
    }*/
}
