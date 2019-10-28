package com.bill.manager;

import com.alibaba.fastjson.JSON;
import com.bill.common.util.LogUtils;
import com.bill.manager.client.UserFeign;
import com.bill.model.dto.ConsumerUserSumBO;
import com.bill.model.enums.ResultEnum;
import com.bill.model.vo.common.ResultVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户微服务manager
 *
 * @author f
 * @date 2019-10-10
 */
@Component
public class UserManager {

    @Autowired
    private UserFeign userFeign;

    /**
     * 更新用户余额
     *
     * @param userName
     * @param remainingSum
     * @return
     */
    public boolean updateRemainingSum(String userName, Long remainingSum) {
        boolean result = false;
        if (StringUtils.isNotEmpty(userName)) {
            ConsumerUserSumBO consumerUserSumBO = new ConsumerUserSumBO();
            consumerUserSumBO.setUserName(userName);
            consumerUserSumBO.setRemainingSum(remainingSum);
            LogUtils.info("UserClient-扣除用户余额：consumerUserSumBO=" + JSON.toJSONString(consumerUserSumBO));
            try {
                ResultVO resultVO = userFeign.updateRemainingSum(consumerUserSumBO);
                LogUtils.info("UserClient-扣除用户余额：resultVO=" + JSON.toJSONString(resultVO));
                if (ResultEnum.SUCCESS.getCode().equals(resultVO.getCode())) {
                    result = true;
                }
            } catch (Exception e) {
                LogUtils.error("UserClient-扣除用户余额异常：consumerUserSumBO=" + JSON.toJSONString(consumerUserSumBO), e);
            }
        }
        return result;
    }
}
