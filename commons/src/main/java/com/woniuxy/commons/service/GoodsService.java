package com.woniuxy.commons.service;

import com.woniuxy.commons.service.factory.GoodsServiceFallbackFactory;
import com.woniuxy.commons.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author: QFX
 * DateTime: 2022-05-17 10:42
 * Description:
 */
@FeignClient(name = "GOODS", fallbackFactory = GoodsServiceFallbackFactory.class) //微服务名字
public interface GoodsService {
    @GetMapping("/goods/all")
    public List<Goods> all();

    @GetMapping("/goods/find/{id}/{idd}")
    public Goods findById(@PathVariable("id") int id, @PathVariable("idd") int idd);

    @PostMapping("/goods/add")
    public ResponseResult<Object> add(@RequestBody Goods goods);

    @GetMapping("/goods/update/{id}/{stock}")
    public ResponseResult<Boolean> update(@PathVariable("id") int id, @PathVariable("stock") int stock) ;
}
