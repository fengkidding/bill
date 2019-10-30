package com.bill.common.handle;

import com.bill.common.util.LogBackUtils;
import com.bill.pattern.factory.ResultVOFactory;
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
        LogBackUtils.error("ExceptionHandle.BindException 抛出的异常:", e);
        return ResultVOFactory.getResult(ResultEnum.VALIDATE_ERROR, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 捕获@Validate校验抛出的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO handleError(MethodArgumentNotValidException e) {
        LogBackUtils.error("ExceptionHandle.MethodArgumentNotValidException 抛出的异常:", e);
        return ResultVOFactory.getResult(ResultEnum.VALIDATE_ERROR, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 捕获业务抛出的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public ResultVO handleError(ServiceException e) {
        LogBackUtils.error("ExceptionHandle.ServiceException 抛出的异常:" + e.getResultEnum(), e);
        return ResultVOFactory.getResult(e.getResultEnum());
    }

    /**
     * 最后同一返回异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Throwable.class)
    public ResultVO handleError(Throwable e) {
        LogBackUtils.error("ExceptionHandle.Throwable 抛出的异常:", e);
        return ResultVOFactory.getErrorResult();
    }
}
