package com.bill.common.log.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * 通用切面
 *
 * @author f
 * @date 2019-11-13
 */
public class CommonJoinPoint {

    /**
     * dao切面
     */
    @Pointcut("execution(* com..dao.*..*(..))")
    public void DaoAccessExecution() {
    }

    /**
     * controller切面
     */
    @Pointcut("execution(public * com.*.*.controller.*..*(..))")
    public void ControllerAccessExecution() {
    }
}
