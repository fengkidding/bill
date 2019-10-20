package com.bill.common.handle;

import com.bill.common.util.LogUtils;
import com.bill.model.enums.ResultEnum;
import com.bill.model.vo.common.ResultVO;
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

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVO handle(Exception e) {
        LogUtils.error("统一异常处理:", e);
        ResultVO resultDto = new ResultVO();
        resultDto.setCode(ResultEnum.ERROR.getCode());
        resultDto.setMsg(ResultEnum.ERROR.getMsg());
        return resultDto;
    }
}
