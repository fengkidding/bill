package com.bill.model.exception;

import com.bill.model.enums.ResultEnum;

/**
 * 业务异常
 *
 * @author f
 * @date 2019-10-25
 */
public class ServiceException extends RuntimeException {

    /**
     * 返回对象枚举
     */
    private ResultEnum resultEnum;

    public ServiceException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.resultEnum = resultEnum;
    }

    public ServiceException(ResultEnum resultEnum, Throwable e) {
        super(resultEnum.getMsg(), e);
        this.resultEnum = resultEnum;
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }

    public void setResultEnum(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }
}
