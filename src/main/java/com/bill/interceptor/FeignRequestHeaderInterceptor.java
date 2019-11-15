package com.bill.interceptor;

import com.bill.common.util.AuthContextUtils;
import com.bill.common.util.RequestCommonUtils;
import com.bill.model.constant.AuthConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Feign调用增加鉴权信息
 *
 * @author f
 * @date 2019-11-08
 */
@Component
public class FeignRequestHeaderInterceptor implements RequestInterceptor {

    /**
     * 请求头增加参数
     *
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String memberId = AuthContextUtils.getMemberId();
        if (StringUtils.isNotBlank(memberId)) {
            requestTemplate.header(AuthConstant.MEMBER_ID, memberId);
            requestTemplate.header("trace_id", RequestCommonUtils.getRequetHeader("trace_id"));
        }
    }
}
