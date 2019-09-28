package com.bill.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * aop
 *
 * @author f
 * @date 2018-04-23
 */
@Aspect
@Component
public class CommonAspect {

    private static final Logger logger = LoggerFactory.getLogger(CommonAspect.class);

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
        String uuid = UUID.randomUUID().toString();
        request.setAttribute("traceId", uuid);
        logger.info("AOP_BEFORE: url=" + request.getRequestURL() + ",traceId=" + uuid + ",logTime=" + System.currentTimeMillis());
    }

    /**
     * 请求之后执行
     */
    @After("log()")
    public void doAfter(JoinPoint joinPoint) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        logger.info("AOP_AFTER: url=" + request.getRequestURL() + ",traceId=" + request.getAttribute("traceId") + ",logTime=" + System.currentTimeMillis());
    }

}
