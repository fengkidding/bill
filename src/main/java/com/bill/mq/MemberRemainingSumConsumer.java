package com.bill.mq;

import com.bill.model.constant.RabbitQueueConstant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 用户余额消费者
 *
 * @author f
 * @date 2019-11-11
 */
@Component
@RabbitListener(queues = RabbitQueueConstant.MEMBER_REMAINING_SUM)
public class MemberRemainingSumConsumer {


}
