package com.bill.manager.client;

import com.bill.model.dto.ConsumerUserSumDto;
import com.bill.model.vo.common.ResultVO;
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
@FeignClient(name = "member")
public interface MemberFeign {

    /**
     * 更新用户余额
     *
     * @param consumerUserSumDto
     * @return
     */
    @RequestMapping(value = "/member/update-remaining-sum", method = RequestMethod.POST)
    ResultVO updateRemainingSum(@RequestBody ConsumerUserSumDto consumerUserSumDto);
}
