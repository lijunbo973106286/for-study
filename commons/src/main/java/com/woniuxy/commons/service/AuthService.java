package com.woniuxy.commons.service;

import com.woniuxy.commons.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @BelongsProject: myspringcloud
 * @BelongsPackage: com.woniuxy.commons.service
 * @Author: qfx
 * @CreateTime: 2022-05-19  11:12
 * @Description: TODO
 * @Version: 1.0
 */
@FeignClient(name = "AUTH")
public interface AuthService {
    @GetMapping("/user/identification/{username}/{permissions}")
    public ResponseResult<Boolean> identification(@PathVariable("username") String username, @PathVariable("permissions") String permissions) ;

}
