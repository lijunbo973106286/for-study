package com.woniuxy.loan.rabbitmq.consumer;

import com.woniuxy.loan.rabbitmq.producer.RepaymentProducer;
import com.woniuxy.loan.service.LoanService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @program: supply-chain-finance
 * @description: 逾期消费者
 * @author: Linqiao Dai
 * @create: 2022-06-08 14:47
 **/
@Component
public class RepaymentConsumer {

    private static final Log log = LogFactory.getLog(RepaymentConsumer.class);

    @Resource
    private LoanService loanService;

    @Resource
    private RepaymentProducer producer;

    @RabbitListener(queues = "plugin_queue")
    public void received(String message){
        if (!message.contains("&")){
            log.info("还款逾期，调用接口增加利息！！！");
            loanService.overdue(message);
        }else {
            log.info("延迟任务续订~~~~~");
            Long delayTime = new Long(message.split("&")[1]);
            producer.produce(delayTime,message.split("&")[0]);
        }
    }


}
