package com.woniuxy.commons.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniuxy.commons.service.AuthService;
import com.woniuxy.commons.annotation.RequirePerms;
import com.woniuxy.commons.util.JWTUtil;
import com.woniuxy.commons.util.ResStatus;
import com.woniuxy.commons.util.ResponseResult;
import com.woniuxy.commons.util.TokenEnum;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @projectName: springcloud86
 * @package: com.woniuxy.commons.interceptor
 * @className: AuthInterceptor
 * @author: SuYHo
 * @description: 拦截器可以拦截contorller中的动态资源，也可以拦截静态资源
 * @date: 2022/5/19 9:48
 * @version: 1.0
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Resource
    @Lazy
    AuthService authService;
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    //    在调用controller中之前执行，返回true放行，返回false拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取调用的方法
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            //2.判断方法上是否有相应注解（权限注解：自定义）
            if (method.isAnnotationPresent(RequirePerms.class)) {
                //3。如果有注解获取注解中的权限信息
                RequirePerms requirePerms = method.getDeclaredAnnotation(RequirePerms.class);
                String perms = requirePerms.value();
                System.out.println(perms);
                //4.获取token，通过token获取用户信息
//                校验码token在里面if的设计初衷是为了实现只有加了RequirePerms注解的uri才需要认证，没加的不需要认证
                String token = request.getHeader("Authorization");
                if (token == null || token.equals("")) {
                    //token为空
                    goLogin(response);
                    //结束请求
                    return false;
                } else {
//                    token不为空，校验
                    TokenEnum result = JWTUtil.verify(token);
                   /* if (result == TokenEnum.TOKEN_EXPIRE){
                        String refreshToken = request.getHeader("refreshToken");
//                        Boolean hasKey = redisTemplate.hasKey(refreshToken);
                        if (Boolean.FALSE.equals(redisTemplate.hasKey(refreshToken))){
                            goLogin(response);
                            return false;
                        }
                            token = JWTUtil.generateToken(JWTUtil.getUname(token));

                            Map<Object, Object> entries = redisTemplate.opsForHash().entries(refreshToken);

                            entries.put("token", token);
                            //重新放回redis
                            redisTemplate.opsForHash().putAll(refreshToken, entries);
                            //重新设置过期时间
                            redisTemplate.expire(refreshToken, 60, TimeUnit.SECONDS);
                            //将token返回给前端
                            response.setHeader("Authorization", token);
                            response.setHeader("Access-Control-Expose-Headers", "Authorization");

//                        合法、获取用户名
                        String uname = JWTUtil.getUname(token);
                        //5.通过openfeign调用认证、鉴权服务鉴定权限
                        ResponseResult<Boolean> identification = authService.identification(uname,perms);
                        //6。返回鉴权结果：true,false
                        if (!identification.getData()){
                            noPerms(response);
                    }
                        return identification.getData();
                    }else if (result == TokenEnum.TOKEN_BAD){
                        System.out.println("token违法===========");
                        goLogin(response);
                        return false;
                    }*/

                    if (result != TokenEnum.TOKEN_SUCCESS) {
//                        非法、过期
                        goLogin(response);
//                        结束请求
                        return false;
                    } else {
//                        合法、获取用户名
                        String uname = JWTUtil.getUname(token);
                        //5.通过openfeign调用认证、鉴权服务鉴定权限
                        ResponseResult<Boolean> identification = authService.identification(uname, perms);
                        //6。返回鉴权结果：true,false
                        if (!identification.getData()) {
                            noPerms(response);
                        }
                        return identification.getData();
                    }
                }
            }
        }


        return super.preHandle(request, response, handler);
    }

    //未登录时返回的结果
    private void goLogin(HttpServletResponse response) throws IOException {
        //没登陆
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(
                new ResponseResult<>(
                        401, "请登录", null, ResStatus.NO_LOGIN)
        ));
    }

    //无权限时返回的结果
    private void noPerms(HttpServletResponse response) throws IOException {
        //没登陆
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(
                new ResponseResult<>(
                        403, "无权限", null, ResStatus.NO_PERMS)
        ));
    }
}
