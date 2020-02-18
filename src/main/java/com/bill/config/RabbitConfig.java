package com.bill.config;

import com.bill.model.constant.RabbitExchangeConstant;
import com.bill.model.constant.RabbitQueueConstant;
import com.bill.model.constant.RabbitRoutingKeyConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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
     * 支付订单队列
     *
     * @return
     */
    @Bean
    public Queue payOrderQueue() {
        return new Queue(RabbitQueueConstant.PAY_ORDER_QUEUE, true);
    }

    /**
     * 支付订单交换机
     *
     * @return
     */
    @Bean
    public TopicExchange payOrderExchange() {
        return new TopicExchange(RabbitExchangeConstant.PAY_ORDER_EXCHANGE);
    }

    /**
     * 绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
     *
     * @return
     */
    @Bean
    public Binding bindingDirect() {
        return BindingBuilder.bind(payOrderQueue()).to(payOrderExchange()).with(RabbitRoutingKeyConstant.PAY_ORDER_EXCHANGE_ROUTING);
    }

}
