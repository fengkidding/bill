package com.bill.service.client;

import com.bill.model.bmo.ConsumerUserSumParamVmo;
import com.bill.model.vmo.common.ResultVmo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户微服务,feign调用微服务，hystrix回退
 *
 * @author f
 * @date 2018-5-8
 */
@FeignClient(name = "user")
public interface UserFeign {

    /**
     * 更新用户余额
     *
     * @param consumerUserSumParamVmo
     * @return
     */
    @RequestMapping(value = "/consumer_user/update_remaining_sum", method = RequestMethod.POST)
    ResultVmo updateRemainingSum(@RequestBody ConsumerUserSumParamVmo consumerUserSumParamVmo);
}
