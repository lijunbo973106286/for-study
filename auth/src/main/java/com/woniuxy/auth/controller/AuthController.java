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
 * @BelongsProject: myspringcloud
 * @BelongsPackage: com.woniuxy.auth.controller
 * @Author: qfx
 * @CreateTime: 2022-05-18  16:25
 * @Description: 登录鉴权
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class AuthController {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
@Resource
@PostMapping("/login")
    public ResponseResult<Object> login(@RequestBody User user, HttpServletResponse response) {
        //根据用户名去数据库查找密码
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
        ResponseResult<Object> responseResult = new ResponseResult<>();
        responseResult.setCode(200);
        responseResult.setStatus(ResStatus.SUCCESS);
        responseResult.setMsg("登录成功");
        return responseResult;

    }

    /**
     * @param username
     * @param permissions
     * @return ResponseResult<Boolean>
     * @description:鉴权
     * @author qfx
     * @date 2022/5/19 11:12
     */

    @GetMapping("/identification/{username}/{permissions}")
    public ResponseResult<Boolean> identification(@PathVariable("username") String username, @PathVariable("permissions") String permissions) {
        List<String> permissionList = Arrays.asList(
                "goods:delete", "goods:add", "goods:find", "order:find", "order:add"
        );
        boolean result = permissionList.contains(permissions);
        ResponseResult<Boolean> responseResult = new ResponseResult<>();
        responseResult.setCode(200);
        responseResult.setStatus(ResStatus.SUCCESS);
        responseResult.setMsg("鉴权成功");
        responseResult.setData(result);
        return responseResult;

    }
}
