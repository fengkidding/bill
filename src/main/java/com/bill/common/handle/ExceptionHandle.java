package com.bill.common.handle;

import com.bill.common.util.LogUtils;
import com.bill.core.factory.ResultFactory;
import com.bill.model.enums.ResultEnum;
import com.bill.model.exception.ServiceException;
import com.bill.model.vo.common.ResultVO;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 *
 * @author f
 * @date 2018-04-23
 */
@RestControllerAdvice
public class ExceptionHandle {

    /**
     * 捕获@Validate校验抛出的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResultVO handleError(BindException e) {
        LogUtils.error("ExceptionHandle.BindException 抛出的异常:", e);
        return ResultFactory.getResult(ResultEnum.VALIDATE_ERROR, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 捕获@Validate校验抛出的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO handleError(MethodArgumentNotValidException e) {
        LogUtils.error("ExceptionHandle.MethodArgumentNotValidException 抛出的异常:", e);
        return ResultFactory.getResult(ResultEnum.VALIDATE_ERROR, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 捕获业务抛出的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public ResultVO handleError(ServiceException e) {
        LogUtils.error("ExceptionHandle.ServiceException 抛出的异常:" + e.getResultEnum(), e);
        return ResultFactory.getResult(e.getResultEnum());
    }

    /**
     * 最后同一返回异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Throwable.class)
    public ResultVO handleError(Throwable e) {
        LogUtils.error("ExceptionHandle.Throwable 抛出的异常:", e);
        return ResultFactory.getErrorResult();
    }
}
