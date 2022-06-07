package com.woniuxy.commons.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniuxy.commons.annotation.RequirePermissions;
import com.woniuxy.commons.service.AuthService;
import com.woniuxy.commons.util.JWTUtil;
import com.woniuxy.commons.util.ResStatus;
import com.woniuxy.commons.util.ResponseResult;
import com.woniuxy.commons.util.TokenEnum;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @BelongsProject: myspringcloud
 * @BelongsPackage: com.woniuxy.commons.interceptor
 * @Author: qfx
 * @CreateTime: 2022-05-19  09:48
 * @Description: 拦截器
 * @Version: 1.0
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Lazy
    @Resource
    private AuthService authService;

    /**
     * @param request
     * @param response
     * @param handler
     * @return boolean
     * @description:该方法会在调用controller方法之前执行
     * @author qfx
     * @date 2022/5/19 9:52
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1、获取该请求调用的方法
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            //2、判断该方法是否有指定的注解（权限注解：自定义）
            if (method.isAnnotationPresent(RequirePermissions.class)) {
                //3、如果有注解，获取该注解的权限信息
                RequirePermissions requirePermissions = method.getDeclaredAnnotation(RequirePermissions.class);
                String perssions = requirePermissions.value();
                System.out.println(perssions);
                //4、得到token，通过token得到当前用户信息
                String token = request.getHeader("Authorization");
                if (token == null || ("").equals(token)) {
                    noLogin(response);
                    System.out.println("----------------------");
                    return false;
                } else {
                    TokenEnum result = JWTUtil.verify(token);
                    if (result == TokenEnum.TOKEN_BAD) {
                        System.out.println("*88888888888888888");
                        noLogin(response);
                        return false;
                    } else {
                        String username = JWTUtil.getUname(token);
                        //5、通过openfeign调用认证、鉴权微服务鉴定是否有权限
                        ResponseResult<Boolean> identification = authService.identification(username, perssions);
                        //6、根据鉴定的结果返回true或false
                        if (!identification.getData()) {
                            noPermissions(response);
                        }
                        return identification.getData();
                    }
                }
            }
        }

        return super.preHandle(request, response, handler);
    }

    /**
     * @param response
     * @description:未登录，返回结果
     * @author qfx
     * @date 2022/5/19 11:00
     */

    private void noLogin(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        ResponseResult<Object> responseResult = new ResponseResult<>();
        responseResult.setCode(401);
        responseResult.setStatus(ResStatus.NO_LOGIN);
        responseResult.setMsg("未登录aaa");
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseResult));
    }

    /**
     * @param response
     * @description:无权限，返回结果
     * @author qfx
     * @date 2022/5/19 11:00
     */

    private void noPermissions(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        ResponseResult<Object> responseResult = new ResponseResult<>();
        responseResult.setCode(403);
        responseResult.setStatus(ResStatus.NO_PERMISSION);
        responseResult.setMsg("无权限");
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseResult));
    }
}
