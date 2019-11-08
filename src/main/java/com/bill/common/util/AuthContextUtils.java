package com.bill.common.util;

import com.bill.model.constant.AuthConstant;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 鉴权验证上下文
 *
 * @author f
 * @date 2019-11-08
 */
public class AuthContextUtils {

    /**
     * 去请求头参数
     *
     * @param headerName
     * @return
     */
    private static String getRequetHeader(String headerName) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            String value = request.getHeader(headerName);
            return value;
        }
        return null;
    }

    /**
     * 获取用户id
     *
     * @return
     */
    public static String getMemberId() {
        return getRequetHeader(AuthConstant.MEMBER_ID);
    }

}
