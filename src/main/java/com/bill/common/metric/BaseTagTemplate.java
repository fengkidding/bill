package com.bill.common.metric;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * 获取tags的方法，各个业务如有自己定制逻辑可以实现此接口，重写获取tag方法
 */
public abstract class BaseTagTemplate {

    /**
     * 获取tags
     * @param joinPoint
     * @return
     */
    public String getTags(JoinPoint joinPoint){
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        return this.buildTags(method);
    }

    /**
     * 构建tags
     * @param method
     * @return
     */
    public abstract String buildTags(Method method);

}
