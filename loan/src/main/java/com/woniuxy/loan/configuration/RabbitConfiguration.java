package com.woniuxy.loan.configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: supply-chain-finance
 * @description: 消息队列配置
 * @author: Linqiao Dai
 * @create: 2022-06-08 11:01
 **/
@Configuration
public class RabbitConfiguration {

    /**
     * 延迟插件
     */
    @Bean
    public Queue pluginQueue(){
        return new Queue("plugin_queue");
    }
    @Bean
    public CustomExchange customExchange(){
        //初始化参数
        Map<String,Object> args = new HashMap<>();

        //参数
        //使用哪种交换机实现延迟交换机
        args.put("x-delayed-type","direct");

        // 参数1：交换机名字
        // 参数2：交换机消息类型
        // 参数3：是否持久化
        // 参数4：是否自动删除
        // 参数5：初始化参数
        return new CustomExchange("plugin_exchange","x-delayed-message",true,false,args);
    }
    @Bean
    public Binding bindingCustomExchangeAndPluginQueue(Queue pluginQueue, CustomExchange customExchange){
        return BindingBuilder.bind(pluginQueue).to(customExchange).with("plugin").noargs();
    }
}
