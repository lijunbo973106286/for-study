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
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: springcloud86
 * @package: com.woniuxy.gateway.filter
 * @className: AuthFilter
 * @author: SuYHo
 * @description: 自定义过滤器，判断url是否需要认证，是否合法
 * @date: 2022/5/18 15:15
 * @version: 1.0
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    //执行过滤操作，写具体的业务
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        1.获取uri
        ServerHttpRequest request = exchange.getRequest();
        String uri = request.getURI().getPath();
        System.out.println(uri);
//      获取response对象
        ServerHttpResponse response = exchange.getResponse();
//        2.判断uri是否需要认证
        if (requireAuth(uri)) {
//        3.需要认证
//        System.out.println("需要认证");
//        4.从请求头中获取token
            //如果请求头中没有Authorization，获取的List为null
            List<String> tokens = request.getHeaders().get("Authorization");
//        5.判断token是否为空，空表示未登录返回一个结果，不为空校验token
            if (tokens != null) {
                String token = tokens.get(0);
//            System.out.println(token);
                //检验token
                TokenEnum result = JWTUtil.verify(token);

                if (result == TokenEnum.TOKEN_EXPIRE) {
                    //获取refreshToken
                    String refreshToken = request.getHeaders().get("refreshToken").get(0);
                    System.out.println("refreshToken:" + refreshToken);
                    //判断refreshToken是否过期
                    Map<Object, Object> entries = redisTemplate.opsForHash().entries(refreshToken);
                    //判断
                    if (Boolean.FALSE.equals(redisTemplate.hasKey(refreshToken))) {
                        return getLogin(response);
                    }
                    //执行到此处证明没过期重新生成token，刷新refreshToken过期时间
                    //从原来的token中获取用户信息
                    token = JWTUtil.generateToken(JWTUtil.getUname(token));
                    //更新redis中的token
                    entries.put("token", token);
                    //重新放回redis
                    redisTemplate.opsForHash().putAll(refreshToken, entries);
                    //重新设置过期时间
                    redisTemplate.expire(refreshToken, 60, TimeUnit.SECONDS);
                    //将token返回给前端
                    response.getHeaders().add("Authorization", token);
                    response.getHeaders().add("Access-Control-Expose-Headers", "Authorization");
                    //将新的token转发给微服务
//                request.getHeaders().set("Authorization",token);
                    ServerHttpRequest.Builder requestBuilder = request.mutate();
                    // 先删除，后新增
                    requestBuilder.headers(k -> k.remove("Authorization"));
                    requestBuilder.header("Authorization", token);
                    ServerHttpRequest requestNew = requestBuilder.build();
                    exchange.mutate().request(requestNew).build();
                } else if (result == TokenEnum.TOKEN_BAD) {
//                无效token
                    return getLogin(response);
                }
            } else {
                //没token,返回登录
                return getLogin(response);
            }
//        7.合法，放行
        }
        return chain.filter(exchange);

    }

    private Mono<Void> getLogin(ServerHttpResponse response) {
        response.getHeaders().add("Context-Type", "application/json;charset=utf-8");
        //返回的数据
        ResponseResult<Object> responseResult = new ResponseResult<>();
        responseResult.setCode(401);
        responseResult.setMsg("请登录");
        responseResult.setStatus(ResStatus.NO_LOGIN);
        try {
            //将对象转换为json
            String data = new ObjectMapper().writeValueAsString(responseResult);
            //将数据返回给浏览器，并停止请求
            DataBuffer buffer = response.bufferFactory().wrap(data.getBytes());
            return response.writeWith(Mono.just(buffer));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    //返回结果，让用户登录

    //    判断uri是否需要认证
    boolean requireAuth(String uri) {
//        罗列不需要认证的uri,uri不写死,应该在配置文件中或者数据库中指定

        //假设j加载到了Uri信息
        List<String> no_auth_uri = Arrays.asList(
                "/user/login",
                "/user/register"
        );
        for (String nau : no_auth_uri) {
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
