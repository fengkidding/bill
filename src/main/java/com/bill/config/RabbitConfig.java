package com.bill.config;

import com.bill.model.constant.RabbitExchangeConstant;
import com.bill.model.constant.RabbitQueueConstant;
import com.bill.model.constant.RabbitRoutingKeyConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbit配置
 *
 * @author f
 * @date 2019-11-11
 */
@Configuration
public class RabbitConfig {

    /**
     * 用户余额队列
     *
     * @return
     */
    @Bean
    public Queue memberRemainingSumQueue() {
        return new Queue(RabbitQueueConstant.MEMBER_REMAINING_SUM, true);
    }

    /**
     * 用户余额交换机
     *
     * @return
     */
    @Bean
    public DirectExchange memberRemainingSumExchange() {
        return new DirectExchange(RabbitExchangeConstant.MEMBER_REMAINING_SUM);
    }

    /**
     * 绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
     *
     * @return
     */
    @Bean
    public Binding bindingDirect() {
        return BindingBuilder.bind(memberRemainingSumQueue()).to(memberRemainingSumExchange()).with(RabbitRoutingKeyConstant.MEMBER_REMAINING_SUM);
    }

}
