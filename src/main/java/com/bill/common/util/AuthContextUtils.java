package com.bill.common.util;

import com.bill.model.constant.AuthConstant;

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

}
