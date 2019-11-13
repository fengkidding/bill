package com.bill.common.log.aop;

import com.bill.common.log.factory.LogMsgFactory;
import com.bill.common.log.model.PerformanceLog;
import com.bill.common.log.LogBackUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 * Controller Aspect
 *
 * @author f
 * @date 2019-11-13
 */
@Aspect
@Configuration
public class ControllerAccessAspect {

    /**
     * Controller日志
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("com.bill.common.log.aop.CommonJoinPoint.ControllerAccessExecution()")
    public Object validate(ProceedingJoinPoint pjp) throws Throwable {
        PerformanceLog performanceLog = LogMsgFactory.getPerformanceLog();
        String packageName = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        String fullName = packageName + "." + methodName;
        performanceLog.setMethodName(fullName);
        performanceLog.start();
        Object result = pjp.proceed();
        performanceLog.stop();
        LogBackUtils.performance(performanceLog);
        return result;
    }

    /**
     * Controller异常日志
     *
     * @param joinPoint
     * @param ex
     */
//    @AfterThrowing(
//            pointcut = "com.bill.common.log.aop.CommonJoinPoint.ControllerAccessExecution()",
//            throwing = "ex"
//    )
//    public void doAfterEx(JoinPoint joinPoint, Throwable ex) {
//        LogUtil.logPerformanceAfterEx(joinPoint, ex);
//    }
}