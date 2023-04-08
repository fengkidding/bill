package com.bill.annotation;

import java.lang.annotation.*;

/**
 * 自定义校验注解，校验手机号
 *
 * @author f
 * @date 2019-10-25
 */
@Documented
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Metric {

    /**
     * 类型
     *
     * @return
     */
    String type() default "";

    /**
     * 名称
     *
     * @return
     */
    String name() default "";

}
