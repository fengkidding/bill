package com.bill.mq;

import com.alibaba.fastjson.JSON;
import com.bill.common.log.LogBackUtils;
import com.bill.manager.MemberManager;
import com.bill.model.constant.RabbitQueueConstant;
import com.bill.model.dto.PayOrderMsgDto;
import com.bill.service.CouponService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 支付订单消费者
 *
 * @author f
 * @date 2019-11-11
 */
@Component
public class PayOrderMsgConsumer {

    @Autowired
    private MemberManager memberManager;

    @Autowired
    private CouponService couponService;

    /**
     * 支付订单消费者
     *
     * @param message
     * @param channel
     */
    @RabbitListener(queues = RabbitQueueConstant.PAY_ORDER_QUEUE)
    public void payOrderMsg(Message message, Channel channel) throws IOException {
        LogBackUtils.info("PayOrderMsgConsumer.payOrderMsg message=" + JSON.toJSONString(message));
        try {
            PayOrderMsgDto payOrderMsgDto = JSON.parseObject(message.getBody(), PayOrderMsgDto.class);
            LogBackUtils.info("PayOrderMsgConsumer.payOrderMsg payOrderMsgDto=" + JSON.toJSONString(payOrderMsgDto));
            if (null != payOrderMsgDto) {
                //用户收钱
                memberManager.updateRemainingSum(payOrderMsgDto.getMemberId(), payOrderMsgDto.getRemainingSum());

                //生产券码
                couponService.increment(payOrderMsgDto.getOrderId());
            }
        } catch (Exception e) {
            LogBackUtils.error("PayOrderMsgConsumer.payOrderMsg异常 message=" + JSON.toJSONString(message), e);
        } finally {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }
}
