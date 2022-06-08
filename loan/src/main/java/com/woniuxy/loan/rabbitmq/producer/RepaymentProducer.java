package com.woniuxy.loan.rabbitmq.producer;

import com.woniuxy.loan.rabbitmq.consumer.RepaymentConsumer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @program: supply-chain-finance
 * @description: 还款定时任务发布
 * @author: Linqiao Dai
 * @create: 2022-06-08 14:05
 **/
@Component
public class RepaymentProducer {

    private static final Log log = LogFactory.getLog(RepaymentProducer.class);

    @Resource
    private AmqpTemplate amqpTemplate;

    public void produce(long delayTime,String repay_time){
        log.info("发布一次"+delayTime/1000+"s延迟消息:"+new Date());
        if (delayTime > Integer.MAX_VALUE){
            delayTime = delayTime - Integer.MAX_VALUE;
            amqpTemplate.convertAndSend("plugin_exchange", "plugin", repay_time+"&"+delayTime, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    // 设置过期时间
                    message.getMessageProperties().setDelay(Integer.MAX_VALUE);
                    return message;
                }
            });
        }else {
            int time = (int) delayTime;
            //延迟队列生产者
            amqpTemplate.convertAndSend("plugin_exchange", "plugin", repay_time, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    // 设置过期时间
                    message.getMessageProperties().setDelay(time);
                    return message;
                }
            });
        }
    }
}
