package com.bill.manager;

import com.alibaba.fastjson.JSON;
import com.bill.common.log.LogBackUtils;
import com.bill.manager.client.MemberFeign;
import com.bill.model.dto.ConsumerUserSumDto;
import com.bill.model.enums.ResultEnum;
import com.bill.model.vo.common.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户微服务manager
 *
 * @author f
 * @date 2019-10-10
 */
@Component
public class MemberManager {

    @Autowired
    private MemberFeign memberFeign;

    /**
     * 更新用户余额
     *
     * @param memberId
     * @param remainingSum
     * @return
     */
    public boolean updateRemainingSum(Integer memberId, Long remainingSum) {
        boolean result = false;
        if (null != memberId) {
            ConsumerUserSumDto consumerUserSumDto = new ConsumerUserSumDto();
            consumerUserSumDto.setMemberId(memberId);
            consumerUserSumDto.setRemainingSum(remainingSum);
            LogBackUtils.info("MemberClient-更新用户余额：consumerUserSumBO=" + JSON.toJSONString(consumerUserSumDto));
            try {
                ResultVO resultVO = memberFeign.updateRemainingSum(consumerUserSumDto);
                LogBackUtils.info("MemberClient-更新用户余额：resultVO=" + JSON.toJSONString(resultVO));
                if (ResultEnum.SUCCESS.getCode().equals(resultVO.getCode())) {
                    result = true;
                }
            } catch (Exception e) {
                LogBackUtils.error("MemberClient-更新用户余额异常：consumerUserSumBO=" + JSON.toJSONString(consumerUserSumDto), e);
            }
        }
        return result;
    }
}
