package com.bill.annotation;

import com.bill.annotation.impl.PhoneNumberValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * 自定义校验注解，校验手机号
 *
 * @author f
 * @date 2019-10-25
 */
@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {

    /**
     * 错误提示信息
     *
     * @return
     */
    String message() default "Invalid phone number";

    /**
     * parameter 必须包含
     * @return
     */
    Class[] groups() default {};

    /**
     * parameter 必须包含
     * @return
     */
    Class[] payload() default {};
}
