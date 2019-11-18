package com.bill.common.util;

import com.bill.model.constant.AuthConstant;
import com.bill.model.enums.ResultEnum;
import com.bill.model.exception.ServiceException;
import org.springframework.util.StringUtils;

/**
 * 鉴权验证上下文
 *
 * @author f
 * @date 2019-11-08
 */
public class AuthContextUtils {

    /**
     * 获取用户id
     *
     * @return
     */
    public static String getMemberId() {
        return RequestCommonUtils.getRequetHeader(AuthConstant.MEMBER_ID);
    }

    /**
     * 业务使用，获取用户id
     *
     * @return
     */
    public static Integer getLoginMemberId() {
        String member = RequestCommonUtils.getRequetHeader(AuthConstant.MEMBER_ID);
        if (StringUtils.isEmpty(member)) {
            throw new ServiceException(ResultEnum.MEMBER_LOGIN_ERROR);
        }
        return Integer.valueOf(member);
    }

}
