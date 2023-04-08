package com.bill.aspect;

import com.bill.common.log.LogBackUtils;
import com.bill.common.metric.BaseTagTemplate;
import com.bill.common.metric.TagTemplateContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 埋点aop
 *
 * @author f
 * @date 2018-04-23
 */
@Aspect
@Component
public class MetricAspect {

    /**
     * AOP处理http请求
     */
    @Pointcut("@annotation(com.bill.annotation.Metric))")
    public void metricCut() {
    }

    /**
     * 请求之前执行
     */
    @Before("metricCut()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            BaseTagTemplate tagTemplate = TagTemplateContext.getTagTemplate("DefaultTagTemplate");
            LogBackUtils.info("MetricAspect: tags=" + tagTemplate.getTags(joinPoint));
        } catch (Exception e) {
            LogBackUtils.error("MetricAspect error", e);
        }
    }

}
