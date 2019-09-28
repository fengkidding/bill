package com.bill.common.handle;

import com.alibaba.fastjson.JSON;
import com.bill.model.bmo.ConsumerUserSumParamVmo;
import com.bill.model.vmo.common.ResultVmo;
import com.bill.service.client.UserFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 *
 * @author f
 * @date 2019-07-02
 */
@Component
public class ScheduleTask {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    @Autowired
    private UserFeign userFeign;

    /**
     * 每个月月末执行
     */
    @Async
    @Scheduled(cron = "0 0 1 1 * ?")
    public void sendMoney() {
        ConsumerUserSumParamVmo consumerUserSumParamVmo = new ConsumerUserSumParamVmo();
        consumerUserSumParamVmo.setUserName("f");
        consumerUserSumParamVmo.setRemainingSum(950000L);
        ResultVmo resultVmo = userFeign.updateRemainingSum(consumerUserSumParamVmo);
        logger.info("定时任务执行更新用户余额：resultVmo=" + JSON.toJSONString(resultVmo));
    }
}
