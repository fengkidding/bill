package com.bill.common.metric;

import java.lang.reflect.Method;

/**
 * aspect中获取tags的方法，默认实现
 */
public class DefaultTagTemplate extends BaseTagTemplate {

    /**
     * 构建tags
     *
     * @param method
     * @return
     */
    @Override
    public String buildTags(Method method) {
        return method.getName();
    }
}
