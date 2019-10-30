package com.bill.annotation.impl;

import com.alibaba.fastjson.JSON;
import com.bill.annotation.PhoneNumber;
import com.bill.common.util.LogBackUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义校验注解，校验手机号
 *
 * @author f
 * @date 2019-10-25
 */
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public void initialize(PhoneNumber phoneNumber) {
        //初始化操作
        LogBackUtils.info(JSON.toJSONString(phoneNumber));
    }

    /**
     * 校验实现方法
     *
     * @param phoneField
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String phoneField, ConstraintValidatorContext context) {
        // can be null
        if (phoneField == null) {
            return true;
        }
        return phoneField != null && phoneField.matches("[0-9]+")
                && (phoneField.length() > 8) && (phoneField.length() < 14);
    }
}
