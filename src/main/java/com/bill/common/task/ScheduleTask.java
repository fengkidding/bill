package com.bill.common.task;

import com.bill.common.util.LogBackUtils;
import com.bill.model.constant.TaskConstant;
import com.bill.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 定时任务
 *
 * @author f
 * @date 2019-07-02
 */
@Component
public class ScheduleTask {

    @Autowired
    private ProductService productService;

    /**
     * 商品过期定时任务
     */
    @Async(TaskConstant.ASYNC_EXECUTOR_NAME)
    @Scheduled(cron = "5 */5 * * * ?")
    public void checkProduct() {
        Integer count = productService.expiredProduct(LocalDateTime.now());
        LogBackUtils.info("ScheduleTask.checkProduct 商品过期定时任务：" + count);
    }
}
