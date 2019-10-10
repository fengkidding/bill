package com.bill.manager.client.impl;

import com.bill.manager.client.UserFeign;
import com.bill.model.bo.ConsumerUserSumBO;
import com.bill.model.enums.ResultEnum;
import com.bill.model.vo.common.ResultVO;
import org.springframework.stereotype.Component;

/**
 * 用户微服务,feign调用微服务，hystrix回退
 *
 * @author f
 * @date 2018-5-8
 */
@Component
public class UserFeignImpl implements UserFeign {

    /**
     * 更新用户余额
     *
     * @param consumerUserSumBO
     * @return
     */
    @Override
    public ResultVO updateRemainingSum(ConsumerUserSumBO consumerUserSumBO) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultEnum.FALL_BACK.getCode());
        resultVO.setMsg(ResultEnum.FALL_BACK.getMsg());
        return resultVO;
    }
}
