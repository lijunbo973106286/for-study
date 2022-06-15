package com.woniuxy.user.controller;

import com.woniuxy.commons.util.JWTUtil;
import com.woniuxy.user.entity.*;
import com.woniuxy.user.service.AccountService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: BackEnd
 * @package: com.woniuxy.user.controller
 * @className: LoginController
 * @author: SuYHo
 * @description: TODO
 * @date: 2022/6/13 15:55
 * @version: 1.0
 */
@RestController
@RequestMapping
public class LoginController {
    @Resource
    RedisTemplate<String, Object> redisTemplate;
    @Resource
    AccountService accountService;

    @PostMapping("/login")
    public ResponseResult<ScfpUser> login(@RequestBody ScfpUser user, HttpServletResponse response) {
        //根据用户名去数据库查找密码
        UserDTO scfpUser = accountService.login(user);
        if (!StringUtils.isEmpty(scfpUser)) {

            //认证成功，生成token
            String token = JWTUtil.generateToken(user.getUname());
            //生成refreshtoken
            String refreshToken = UUID.randomUUID().toString();
            //封装数据
            Map<String, Object> values = new HashMap<>();
            values.put("token", token);
            values.put("uname", user.getUname());
            //token放到redis
            redisTemplate.opsForHash().putAll(refreshToken, values);
            //将token放到响应头，返回前端
            response.setHeader("Authorization", token);
            response.setHeader("refreshtoken", refreshToken);
            //设置过期时间
            redisTemplate.expire(refreshToken, 60, TimeUnit.SECONDS);
            //暴露响应头，使前端能够访问
            response.setHeader("Access-Control-Expose-Headers", "Authorization,refreshtoken");
            //返回结果
            return new ResponseResult<>(200, "登录成功", scfpUser, ResStatus.LOGIN_SUCCESS);
        }
        return new ResponseResult<>(500, "登录失败", null, ResStatus.FAIL);
    }

    /**
     * 注册
     *
     * @param register
     * @return
     */
    @PostMapping("/register")
    ResponseResult register(@RequestBody Register register) {
        return accountService.register(register);
    }
}
