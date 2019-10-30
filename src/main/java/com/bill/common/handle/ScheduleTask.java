package com.bill.common.handle;

import com.bill.common.util.LogBackUtils;
import com.bill.manager.UserManager;
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

    @Autowired
    private UserManager userClient;

    /**
     * 每个月月末执行
     */
    @Async
    @Scheduled(cron = "0 0 1 1 * ?")
    public void sendMoney() {
        Boolean result = userClient.updateRemainingSum("f", 950000L);
        LogBackUtils.info("定时任务执行更新用户余额：result=" + result);
    }
}
