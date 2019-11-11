package com.bill.mq;

import com.alibaba.fastjson.JSON;
import com.bill.common.util.LogBackUtils;
import com.bill.manager.MemberManager;
import com.bill.model.constant.RabbitQueueConstant;
import com.bill.model.dto.ConsumerUserSumBO;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 用户余额消费者
 *
 * @author f
 * @date 2019-11-11
 */
@Component
public class MemberRemainingSumConsumer {

    @Autowired
    private MemberManager memberManager;

    /**
     * 售卖商品,用户余额消费者
     *
     * @param message
     * @param channel
     */
    @RabbitListener(queues = RabbitQueueConstant.MEMBER_REMAINING_SUM)
    public void updateMemberRemainingSum(Message message, Channel channel) throws IOException {
        LogBackUtils.info("MemberRemainingSumConsumer.updateMemberRemainingSum message=" + JSON.toJSONString(message));
        try {
            ConsumerUserSumBO consumerUserSumBO = JSON.parseObject(message.getBody(), ConsumerUserSumBO.class);
            LogBackUtils.info("MemberRemainingSumConsumer.updateMemberRemainingSum consumerUserSumBO=" + JSON.toJSONString(consumerUserSumBO));
            if (null != consumerUserSumBO) {
                Long remainingSum = consumerUserSumBO.getRemainingSum();
                remainingSum = remainingSum - 100;
                memberManager.updateRemainingSum(consumerUserSumBO.getUserName(), remainingSum);
            }
        } catch (Exception e) {
            LogBackUtils.error("MemberRemainingSumConsumer.updateMemberRemainingSum异常 message=" + JSON.toJSONString(message), e);
        } finally {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }
}
