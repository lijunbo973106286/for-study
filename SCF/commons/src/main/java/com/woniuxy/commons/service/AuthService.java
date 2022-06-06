package com.woniuxy.commons.service;

import com.woniuxy.commons.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @projectName: springcloud86
 * @package: com.woniuxy.commons.service
 * @className: AuthService
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/5/19 11:13
 * @version: 1.0
 */
@FeignClient(name = "AUTH")
public interface AuthService {
    @GetMapping("/user/indent/{uname}/{perms}")
    ResponseResult<Boolean> identification(@PathVariable("uname") String uname, @PathVariable("perms") String perms);
}
