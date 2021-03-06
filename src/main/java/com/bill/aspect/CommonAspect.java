package com.bill.aspect;

import com.bill.common.log.LogBackUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * aop
 *
 * @author f
 * @date 2018-04-23
 */
@Aspect
@Component
public class CommonAspect {

    /**
     * AOP处理http请求
     */
    @Pointcut("execution(public * com.bill.controller.*.*(..))")
    public void log() {
    }

    /**
     * 请求之前执行
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        LogBackUtils.info("AOP_BEFORE: url=" + request.getRequestURL());
    }

    /**
     * 请求之后执行
     */
    @After("log()")
    public void doAfter(JoinPoint joinPoint) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        LogBackUtils.info("AOP_AFTER: url=" + request.getRequestURL());
    }

}
