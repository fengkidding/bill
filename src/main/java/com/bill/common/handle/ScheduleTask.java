package com.bill.common.handle;

import com.bill.manager.UserClient;
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
    private UserClient userClient;

    /**
     * 每个月月末执行
     */
    @Async
    @Scheduled(cron = "0 0 1 1 * ?")
    public void sendMoney() {
        Boolean result = userClient.updateRemainingSum("f", 950000L);
        logger.info("定时任务执行更新用户余额：result=" + result);
    }
}
