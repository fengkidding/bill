package com.bill.common.handle;

import com.bill.model.enums.ResultEnum;
import com.bill.model.vo.common.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 *
 * @author f
 * @date 2018-04-23
 */
@ControllerAdvice
public class ExceptionHandle {

    private final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVO handle(Exception e) {
        logger.error("统一异常处理:", e);
        ResultVO resultDto = new ResultVO();
        resultDto.setCode(ResultEnum.ERROR.getCode());
        resultDto.setMsg(ResultEnum.ERROR.getMsg());
        return resultDto;
    }
}
