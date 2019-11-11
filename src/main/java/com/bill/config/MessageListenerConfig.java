package com.bill.config;

import com.bill.mq.MemberRemainingSumConsumer;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbit设置手动确认配置
 *
 * @author f
 * @date 2019-11-11
 */
@Configuration
public class MessageListenerConfig {

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private MemberRemainingSumConsumer memberRemainingSumConsumer;

    @Autowired
    private RabbitConfig rabbitConfig;

    /**
     * 用户余额，RabbitMQ默认是自动确认，这里改为手动确认消息
     *
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        // RabbitMQ默认是自动确认，这里改为手动确认消息
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setQueues(rabbitConfig.memberRemainingSumQueue());
        container.setMessageListener(memberRemainingSumConsumer);
        return container;
    }

}
