package com.woniuxy.commons.service.factory;

import com.woniuxy.commons.entity.Goods;
import com.woniuxy.commons.service.GoodsService;
import com.woniuxy.commons.util.ResponseResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @BelongsProject: myspringcloud
 * @BelongsPackage: com.woniuxy.commons.service.factory
 * @Author: qfx
 * @CreateTime: 2022-05-17  15:17
 * @Description: 降级工厂
 * @Version: 1.0
 */
@Component
public class GoodsServiceFallbackFactory implements FallbackFactory<GoodsService> {
    @Override
    public GoodsService create(Throwable throwable) {
        /**
         * @description:feign接口对应方法的降级方法
         * @author qfx
         * @date 2022/5/17 15:20
         * @param throwable
         * @return GoodsService
         */
        return new GoodsService() {
            @Override
            public List<Goods> all() {
                return Arrays.asList(
                        new Goods("降级服务", 500)
                );
            }

            @Override
            public Goods findById(int id, int idd) {
                return new Goods("降级服务",500);
            }

            @Override
            public ResponseResult<Object> add(Goods goods) {
                return null;
            }

            @Override
            public ResponseResult<Boolean> update(int id, int stock) {
                return null;
            }
        };
    }
}
