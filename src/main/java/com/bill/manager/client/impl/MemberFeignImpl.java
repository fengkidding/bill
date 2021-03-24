package com.bill.manager.client.impl;

import com.bill.pattern.factory.ResultVOFactory;
import com.bill.manager.client.MemberFeign;
import com.bill.model.dto.ConsumerUserSumDto;
import com.bill.model.vo.common.ResultVO;
import org.springframework.stereotype.Component;

/**
 * 用户微服务,feign调用微服务，hystrix回退
 *
 * @author f
 * @date 2018-5-8
 */
@Component
public class MemberFeignImpl implements MemberFeign {

    /**
     * 更新用户余额
     *
     * @param consumerUserSumBO
     * @return
     */
    @Override
    public ResultVO updateRemainingSum(ConsumerUserSumDto consumerUserSumBO) {
        return ResultVOFactory.getFallBackResult();
    }
}
