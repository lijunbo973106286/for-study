package com.woniuxy.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woniuxy.commons.util.JWTUtil;
import com.woniuxy.commons.util.ResStatus;
import com.woniuxy.commons.util.ResponseResult;
import com.woniuxy.commons.util.TokenEnum;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: myspringcloud
 * @BelongsPackage: com.woniuxy.gateway.filter
 * @Author: qfx
 * @CreateTime: 2022-05-18  15:15
 * @Description: 自定义认证过滤器，根据uri判断是否需要认证，token是否合法
 * @Version: 1.0
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @param exchange
     * @param chain
     * @return Mono<Void>
     * @description:执行过滤操作，写具体业务
     * @author qfx
     * @date 2022/5/18 15:18
     */

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1、获取uri
        ServerHttpRequest request = exchange.getRequest();
        String uri = request.getURI().getPath();
        System.out.println("--------" + uri + "-------");
        //获取response
        ServerHttpResponse response = exchange.getResponse();
        //2、判断该uri是否需要认证
        if (requireAuth(uri)) {
            //3、需要认证
            System.out.println("需要认证");
            //4、从请求头获取token
            List<String> tokens = request.getHeaders().get("Authorization");
            //5、判断token是否为空：空表示未登录，返回一个结果，不为空：校验token
            if (tokens != null) {
                String token = tokens.get(0);
                System.out.println(token);
                //6、校验token：非法、过期返回结果，去登录
                TokenEnum result = JWTUtil.verify(token);
                if (result == TokenEnum.TOKEN_EXPIRE) {
                    String refreshtoken = request.getHeaders().get("refreshtoken").get(0);
                    Map<Object, Object> entries = redisTemplate.opsForHash().entries("refreshtoken");
                    if (!redisTemplate.hasKey(refreshtoken)) {
                        return goLogin(response);
                    }
                    token = JWTUtil.generateToken(JWTUtil.getUname(token));
                    Map<String, Object> values = new HashMap<>();
                    values.put("token", token);
                    values.put("username", JWTUtil.getUname(token));
                    redisTemplate.opsForHash().putAll(refreshtoken, values);
                    redisTemplate.expire(refreshtoken, 60, TimeUnit.SECONDS);
                    response.getHeaders().add("Authorization", token);
                    response.getHeaders().add("Access-Control-Expose-Headers", "Authorization");
                    HttpHeaders headers = request.getHeaders();
                    //设置请求头为可修改的
                    headers = HttpHeaders.writableHttpHeaders(headers);
                    //设置请求头
                    headers.set("Authorization", token);
                    //方法二 bulid
//                    exchange.getRequest().mutate().header("Authorization",token).build();
                } else if (result == TokenEnum.TOKEN_BAD) {
                    //非法，结束请求，返回结果叫别人登录
                    Mono<Void> response1 = goLogin(response);
                    if (response1 != null) {
                        return response1;
                    }
                }
            } else {
                //无token，返回结果叫别人登录
                Mono<Void> response1 = goLogin(response);
                if (response1 != null) {
                    return response1;
                }
            }
        }
        //7、合法，放行
        return chain.filter(exchange);
    }

    /**
     * @param response
     * @return Mono<Void>
     * @description:返回结果，叫用户登录
     * @author qfx
     * @date 2022/5/18 17:34
     */

    private Mono<Void> goLogin(ServerHttpResponse response) {
        response.getHeaders().add("Content-Type", "application/json;charset=utf-8");
        ResponseResult<Object> responseResult = new ResponseResult<>();
        responseResult.setCode(401);
        responseResult.setStatus(ResStatus.NO_LOGIN);
        responseResult.setMsg("用户未登录，请先登录");
        //将对象转换成json
        try {
            String data = new ObjectMapper().writeValueAsString(responseResult);
            //将对象返回浏览器，并终止当前请求
            DataBuffer buffer = response.bufferFactory().wrap(data.getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Mono.just(buffer));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param uri
     * @return boolean
     * @description:判断uri是否需要认证
     * @author qfx
     * @date 2022/5/18 15:32
     */

    private boolean requireAuth(String uri) {
        //uri不写死，读取配置文件或数据库
        //模拟数据
        List<String> noAuthUri = Arrays.asList("/user/login", "/user/regist","/kill/start","/network/all");
        for (String nau : noAuthUri
        ) {
            if (uri.startsWith(nau)) {
                return false;
            }
        }
        return true;

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
