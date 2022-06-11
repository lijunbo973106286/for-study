package com.woniuxy.auth.controller;

import com.woniuxy.auth.entity.User;
import com.woniuxy.commons.util.JWTUtil;
import com.woniuxy.commons.util.ResStatus;
import com.woniuxy.commons.util.ResponseResult;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: springcloud86
 * @package: com.woniuxy.auth.controller
 * @className: AuthController
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/5/18 16:31
 * @version: 1.0
 */
@RestController
@RequestMapping("/user")
public class AuthController {
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    //    认证
    @PostMapping("/login")
    ResponseResult<Object> login(@RequestBody User user, HttpServletResponse response) {
        //通过账号去数据库查数据然后对比密码

        //假设认证成功
        //生成token
        String token = JWTUtil.generateToken(user.getUname());
        //生成refreshToken
        String refreshToken = UUID.randomUUID().toString();
        //封装数据
        Map<String, Object> values = new HashMap<>();
        values.put("token", token);
        values.put("uname", user.getUname());
        //把token放到redis
        redisTemplate.opsForHash().putAll(refreshToken, values);
        //设置过期时间
        redisTemplate.expire(refreshToken, 60, TimeUnit.SECONDS);
        //将token放到响应头中返回给前端
        response.setHeader("Authorization", token);
        response.setHeader("refreshToken", refreshToken);
        //暴露头
        response.setHeader("Access-Control-Expose-Headers", "Authorization,refreshToken");
        return new ResponseResult<>(200, "登录成功", null, ResStatus.LOGIN_SUCCESS);
    }

    //鉴权
    @GetMapping("/indent/{uname}/{perms}")
    ResponseResult<Boolean> identification(@PathVariable("uname") String uname, @PathVariable("perms") String perms) {
        List<String> perms_list = Arrays.asList(
                "goods:find",
                "goods:add",
                "goods:del",
                "order:find",
                "order:add"
        );
        boolean result = perms_list.contains(perms);
        return new ResponseResult<>(200, "", result, ResStatus.SUCCESS);
    }
}
